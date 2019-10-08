package edu.eci.arsw.digitalqueue.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.assembler.QueueResourceAssembler;
import edu.eci.arsw.digitalqueue.repository.QueueRepository;

@RestController
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private QueueResourceAssembler queueResourceAssembler;

    @GetMapping
    public Resources<Resource<Queue>> all(){
        
    }

}
