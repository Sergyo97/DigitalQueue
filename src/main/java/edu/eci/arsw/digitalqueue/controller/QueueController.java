package edu.eci.arsw.digitalqueue.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.assembler.QueueResourceAssembler;
import edu.eci.arsw.digitalqueue.exception.QueueNotFoundException;
import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.repository.QueueRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "queues", produces = "application/json")
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private QueueResourceAssembler queueResourceAssembler;

    @GetMapping
    public Resources<Resource<Queue>> all() {
        List<Resource<Queue>> queues = queueRepository.findAll().stream().map(queueResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(queues, linkTo(methodOn(QueueController.class).all()).withSelfRel());
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody Queue newQueue) throws URISyntaxException {
        Resource<Queue> resource = queueResourceAssembler.toResource(queueRepository.save(newQueue));

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping("/{id}")
    public Resource<Queue> one(@PathVariable Long id) {
        Queue queue = queueRepository.findById(id).orElseThrow(() -> new QueueNotFoundException(id));

        return queueResourceAssembler.toResource(queue);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Resource<Queue>> update(@PathVariable Long id, @RequestBody Queue newQueue)
            throws URISyntaxException {
        Queue updatedQueue = queueRepository.findById(id).map(queue -> {
            queue.setName(newQueue.getName());
            return queueRepository.save(queue);
        }).orElseGet(() -> {
            newQueue.setId(id);
            return queueRepository.save(newQueue);
        });

        Resource<Queue> resource = queueResourceAssembler.toResource(updatedQueue);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> delete(@PathVariable Long id) {
        queueRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
