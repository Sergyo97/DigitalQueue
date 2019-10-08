package edu.eci.arsw.digitalqueue.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.assembler.QueueResourceAssembler;
import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.repository.QueueRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private QueueResourceAssembler queueResourceAssembler;

    @GetMapping
    public Resources<Resource<Queue>> all(){
        List<Resource<Queue>> queues = queueRepository.findAll().stream()
            .map(queueResourceAssembler::toResource)
            .collect(Collectors.toList());
            
        return new Resources<>(queues, 
            linkTo(methodOn(QueueController.class).all()).withSelfRel());
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody Queue newQueue) throws URISyntaxException{
        Resource<Queue> resource = queueResourceAssembler.toResource(queueRepository.save(newQueue));

        return ResponseEntity
            .created(new URI(resource.getId().expand().getHref()))
            .body(resource);
    }

}
