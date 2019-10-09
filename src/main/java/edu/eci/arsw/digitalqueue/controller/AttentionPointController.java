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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.assembler.AttentionPointResourceAssembler;
import edu.eci.arsw.digitalqueue.exception.AttentionPointNotFoundException;
import edu.eci.arsw.digitalqueue.model.AttentionPoint;
import edu.eci.arsw.digitalqueue.repository.AttentionPointRepository;

/**
 * AttentionPointController
 */
@CrossOrigin
@RestController
@RequestMapping(value = "attentionPoints", produces = "application/json")
public class AttentionPointController {

    @Autowired
    private AttentionPointRepository attentionPointRepository;

    @Autowired
    private AttentionPointResourceAssembler attentionPointResourceAssembler;

    @GetMapping
    public Resources<Resource<AttentionPoint>> all() {
        List<Resource<AttentionPoint>> attentionPoints = attentionPointRepository.findAll().stream()
                .map(attentionPointResourceAssembler::toResource).collect(Collectors.toList());

        return new Resources<>(attentionPoints, linkTo(methodOn(AttentionPointController.class).all()).withSelfRel());
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody AttentionPoint newAttentionPoint) throws URISyntaxException {
        Resource<AttentionPoint> resource = attentionPointResourceAssembler
                .toResource(attentionPointRepository.save(newAttentionPoint));

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping("/{id}")
    public Resource<AttentionPoint> one(@PathVariable Long id) {
        AttentionPoint attentionPoint = attentionPointRepository.findById(id)
                .orElseThrow(() -> new AttentionPointNotFoundException(id));

        return attentionPointResourceAssembler.toResource(attentionPoint);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Resource<AttentionPoint>> update(@PathVariable Long id, @RequestBody AttentionPoint newAttentionPoint)
            throws URISyntaxException {
        AttentionPoint updatedAttentionPoint = attentionPointRepository.findById(id).map(attentionPoint -> {
            attentionPoint.setCode(newAttentionPoint.getCode());
            attentionPoint.setEmployee(newAttentionPoint.getEmployee());
            attentionPoint.setEnable(newAttentionPoint.getEnable());
            return attentionPointRepository.save(attentionPoint);
        }).orElseGet(() -> {
            newAttentionPoint.setId(id);
            return attentionPointRepository.save(newAttentionPoint);
        });

        Resource<AttentionPoint> resource = attentionPointResourceAssembler.toResource(updatedAttentionPoint);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> delete(@PathVariable Long id) {
        attentionPointRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}