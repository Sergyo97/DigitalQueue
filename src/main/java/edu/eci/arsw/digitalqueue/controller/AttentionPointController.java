package edu.eci.arsw.digitalqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.repository.AttentionPointRepository;

/**
 * AttentionPointController
 */
@CrossOrigin
@RestController
@RequestMapping("attentionPoints")
public class AttentionPointController {

    @Autowired
    private AttentionPointRepository attentionPointRepository;

    @Autowired
    private Atte

}