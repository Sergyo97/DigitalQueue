package edu.eci.arsw.digitalqueue.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.assembler.TurnResourceAssembler;
import edu.eci.arsw.digitalqueue.exception.NoTurnsInQueueException;
import edu.eci.arsw.digitalqueue.exception.TurnAlreadyCancelledException;
import edu.eci.arsw.digitalqueue.exception.TurnNotFoundException;
import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.model.Turn;
import edu.eci.arsw.digitalqueue.repository.TurnRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "turns", produces = "application/json")
public class TurnController {

    @Autowired
    private TurnRepository turnRepository;

    @Autowired
    private TurnResourceAssembler turnResourceAssembler;

    @GetMapping
    public Resources<Resource<Turn>> all() {
        List<Resource<Turn>> turns = turnRepository.findAll().stream().map(turnResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(turns, linkTo(methodOn(TurnController.class).all()).withSelfRel());
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody Turn newTurn) throws URISyntaxException {
        Resource<Turn> resource = turnResourceAssembler.toResource(turnRepository.save(newTurn));

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping("/{code}")
    public Resource<Turn> one(@PathVariable String code) {
        Turn turn = turnRepository.findById(code).orElseThrow(() -> new TurnNotFoundException(code));

        return turnResourceAssembler.toResource(turn);
    }

    @GetMapping("/{queue}")
    public Resources<Resource<Turn>> inQueue(@PathVariable Queue queue) {
        List<Resource<Turn>> turns = turnRepository.findByQueueAndAttendedFalseOrderByRequestedDateTimeDesc(queue)
                .stream().map(turnResourceAssembler::toResource).collect(Collectors.toList());

        return new Resources<>(turns, linkTo(methodOn(TurnController.class).all()).withSelfRel());
    }

    @GetMapping("/{queue}/next")
    public Resource<Turn> nextInQueue(@PathVariable Queue queue) {
        Turn turn = turnRepository.findFirstByQueueAndAttendedFalseOrderByRequestedDateTimeDesc(queue)
                .orElseThrow(() -> new NoTurnsInQueueException(queue.getName()));

        return turnResourceAssembler.toResource(turn);
    }

    @PutMapping("/{code}")
    private ResponseEntity<Resource<Turn>> update(@PathVariable String code, @RequestBody Turn newTurn)
            throws URISyntaxException {
        Turn updatedTurn = turnRepository.findById(code).map(turn -> {
            turn.setAttended(newTurn.getAttended());
            turn.setAttendedDateTime(newTurn.getAttendedDateTime());
            turn.setAttentionPoint(newTurn.getAttentionPoint());
            turn.setCancelled(newTurn.getCancelled());
            turn.setClientName(newTurn.getClientName());
            turn.setQueue(newTurn.getQueue());
            turn.setRequestedDateTime(newTurn.getRequestedDateTime());
            return turnRepository.save(turn);
        }).orElseGet(() -> {
            newTurn.setCode(code);
            return turnRepository.save(newTurn);
        });

        Resource<Turn> resource = turnResourceAssembler.toResource(updatedTurn);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @PutMapping("/{code}/cancel")
    private ResponseEntity<Resource<Turn>> cancel(@PathVariable String code) throws URISyntaxException {
        Turn updatedTurn = turnRepository.findById(code).orElseThrow(() -> new TurnNotFoundException(code));
        if (updatedTurn.getCancelled()) {
            throw new TurnAlreadyCancelledException(code);
        }
        updatedTurn.setCancelled(true);

        Resource<Turn> resource = turnResourceAssembler.toResource(updatedTurn);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

}
