package edu.eci.arsw.digitalqueue.assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import edu.eci.arsw.digitalqueue.controller.AttentionPointController;
import edu.eci.arsw.digitalqueue.model.AttentionPoint;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * AttentionPointAssembler
 */
@Component
public class AttentionPointResourceAssembler implements ResourceAssembler<AttentionPoint, Resource<AttentionPoint>> {

    @Override
    public Resource<AttentionPoint> toResource(AttentionPoint attentionPoint) {
        return new Resource<AttentionPoint>(attentionPoint,
                linkTo(methodOn(AttentionPointController.class).one(attentionPoint.getId())).withSelfRel(),
                linkTo(methodOn(AttentionPointController.class).all()).withRel("attentionPoints"));
    }

}