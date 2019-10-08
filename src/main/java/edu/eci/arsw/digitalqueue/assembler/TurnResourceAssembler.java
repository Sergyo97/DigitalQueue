package edu.eci.arsw.digitalqueue.assembler;

import edu.eci.arsw.digitalqueue.controller.TurnController;
import edu.eci.arsw.digitalqueue.model.Turn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TurnResourceAssembler  implements ResourceAssembler<Turn, Resource<Turn>>{

    @Override
    public Resource<Turn> toResource(Turn turn) {
        return new Resource<Turn>(turn, 
                linkTo(methodOn(TurnController.class).one(turn.getCode())).withSelfRel(),
                linkTo(TurnController.class).all().withRel("turns"));
    }
}

