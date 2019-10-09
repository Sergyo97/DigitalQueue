package edu.eci.arsw.digitalqueue.assembler;

import edu.eci.arsw.digitalqueue.controller.QueueController;
import edu.eci.arsw.digitalqueue.model.Queue;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class QueueResourceAssembler implements ResourceAssembler<Queue, Resource<Queue>> {

    @Override
    public Resource<Queue> toResource(Queue queue) {
        return new Resource<>(queue, linkTo(methodOn(QueueController.class).one(queue.getId())).withSelfRel(),
                linkTo(methodOn(QueueController.class).all()).withRel("queues"));
    }
}
