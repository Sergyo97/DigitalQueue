package edu.eci.arsw.digitalqueue.controller;

import edu.eci.arsw.digitalqueue.assembler.ServiceRepresentationModelAssembler;
import edu.eci.arsw.digitalqueue.exception.ServiceNotFoundException;
import edu.eci.arsw.digitalqueue.model.Service;
import edu.eci.arsw.digitalqueue.repository.ServiceRepository;
import org.springframework.amqp.core.*;
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

@RestController
@RequestMapping(value = "services", produces = "application/json")
@SuppressWarnings("unused")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceRepresentationModelAssembler serviceRepresentationModelAssembler;

    @Autowired
    private DirectExchange turnsDirect;

    @GetMapping
    public CollectionModel<EntityModel<Service>> all() {
        List<EntityModel<Service>> queues = serviceRepository.findAll().stream().map(serviceRepresentationModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(queues, linkTo(ServiceController.class).withSelfRel());
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody Service newService) throws URISyntaxException {
        Queue serviceQueue = QueueBuilder.durable(newService.getName()).build();
        newService.setQueue(serviceQueue);
        BindingBuilder.bind(serviceQueue).to(turnsDirect).with(newService.getIdentifier());
        EntityModel<Service> entityModel = serviceRepresentationModelAssembler.toModel(serviceRepository.save(newService));

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

    @GetMapping("/{name}")
    public EntityModel<Service> one(@PathVariable String name) {
        Service queue = serviceRepository.findByName(name).orElseThrow(() -> new ServiceNotFoundException(name));

        return serviceRepresentationModelAssembler.toModel(queue);
    }

    @PutMapping("/{id}")
    private ResponseEntity<EntityModel<Service>> update(@PathVariable Long id, @RequestBody Service newService)
            throws URISyntaxException {
        Service updatedService = serviceRepository.findById(id).map(service -> {
            service.setName(newService.getName());
            return serviceRepository.save(service);
        }).orElseGet(() -> {
            newService.setId(id);
            return serviceRepository.save(newService);
        });

        EntityModel<Service> entityModel = serviceRepresentationModelAssembler.toModel(updatedService);

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> delete(@PathVariable Long id) {
        serviceRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
