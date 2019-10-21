package edu.eci.arsw.digitalqueue.assembler;

import edu.eci.arsw.digitalqueue.controller.QueueController;
import edu.eci.arsw.digitalqueue.model.Queue;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class QueueRepresentationModelAssembler implements RepresentationModelAssembler<Queue, EntityModel<Queue>> {

    @Override
    public EntityModel<Queue> toModel(Queue queue) {
        return new EntityModel<>(queue,
                linkTo(QueueController.class).slash(queue.getId()).withSelfRel(),
                linkTo(QueueController.class).withRel("queues"));
    }
}
