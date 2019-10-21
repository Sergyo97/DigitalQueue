package edu.eci.arsw.digitalqueue.controller;

import edu.eci.arsw.digitalqueue.assembler.AttentionPointRepresentationModelAssembler;
import edu.eci.arsw.digitalqueue.exception.AttentionPointNotFoundException;
import edu.eci.arsw.digitalqueue.model.AttentionPoint;
import edu.eci.arsw.digitalqueue.repository.AttentionPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
    private AttentionPointRepresentationModelAssembler attentionPointRepresentationModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<AttentionPoint>> all() {
        List<EntityModel<AttentionPoint>> attentionPoints = attentionPointRepository.findAll().stream()
                .map(attentionPointRepresentationModelAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(attentionPoints, linkTo(AttentionPointController.class).withSelfRel());
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody AttentionPoint newAttentionPoint) throws URISyntaxException {
        EntityModel<AttentionPoint> entityModel = attentionPointRepresentationModelAssembler
                .toModel(attentionPointRepository.save(newAttentionPoint));

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<AttentionPoint> one(@PathVariable Long id) {
        AttentionPoint attentionPoint = attentionPointRepository.findById(id)
                .orElseThrow(() -> new AttentionPointNotFoundException(id));

        return attentionPointRepresentationModelAssembler.toModel(attentionPoint);
    }

    @PutMapping("/{id}")
    private ResponseEntity<EntityModel<AttentionPoint>> update(@PathVariable Long id, @RequestBody AttentionPoint newAttentionPoint)
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

        EntityModel<AttentionPoint> entityModel = attentionPointRepresentationModelAssembler.toModel(updatedAttentionPoint);

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> delete(@PathVariable Long id) {
        attentionPointRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}