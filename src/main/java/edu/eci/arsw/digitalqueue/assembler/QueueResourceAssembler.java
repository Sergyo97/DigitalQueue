package edu.eci.arsw.digitalqueue.assembler;

/**
 * QueueResourceAssembler
 */

@Component
public class QueueResourceAssembler implements ResourceAssembler<Queue, Resource<Queue>> {

    @Override
    public Resource<Queue> toResource(Queue Queue) {
        return new Resource<>(Queue, linkTo(methodOn(QueueController.class).one(Queue.getId())).withSelfRel(),
                linkTo(methodOn(QueueController.class).all()).withRel("Queues"));
    }
}