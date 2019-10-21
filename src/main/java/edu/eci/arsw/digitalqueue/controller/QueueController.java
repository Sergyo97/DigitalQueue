package edu.eci.arsw.digitalqueue.controller;

import edu.eci.arsw.digitalqueue.assembler.QueueRepresentationModelAssembler;
import edu.eci.arsw.digitalqueue.exception.QueueNotFoundException;
import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.repository.QueueRepository;
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

@CrossOrigin
@RestController
@RequestMapping(value = "queues", produces = "application/json")
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private QueueRepresentationModelAssembler queueRepresentationModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Queue>> all() {
        List<EntityModel<Queue>> queues = queueRepository.findAll().stream().map(queueRepresentationModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(queues, linkTo(QueueController.class).withSelfRel());
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody Queue newQueue) throws URISyntaxException {
        EntityModel<Queue> entityModel = queueRepresentationModelAssembler.toModel(queueRepository.save(newQueue));

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Queue> one(@PathVariable Long id) {
        Queue queue = queueRepository.findById(id).orElseThrow(() -> new QueueNotFoundException(id));

        return queueRepresentationModelAssembler.toModel(queue);
    }

    @PutMapping("/{id}")
    private ResponseEntity<EntityModel<Queue>> update(@PathVariable Long id, @RequestBody Queue newQueue)
            throws URISyntaxException {
        Queue updatedQueue = queueRepository.findById(id).map(queue -> {
            queue.setName(newQueue.getName());
            return queueRepository.save(queue);
        }).orElseGet(() -> {
            newQueue.setId(id);
            return queueRepository.save(newQueue);
        });

        EntityModel<Queue> entityModel = queueRepresentationModelAssembler.toModel(updatedQueue);

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> delete(@PathVariable Long id) {
        queueRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
