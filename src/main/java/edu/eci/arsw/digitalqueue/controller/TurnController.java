package edu.eci.arsw.digitalqueue.controller;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.model.Turn;
import edu.eci.arsw.digitalqueue.service.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TurnController {

    @Autowired
    private
    TurnService turnService;

    @GetMapping("/turns")
    List<Turn> all() {
        return turnService.findAll();
    }

    @PostMapping("/turns")
    public Turn create(@RequestBody Turn newTurn) {
        return turnService.create(newTurn);
    }

    @GetMapping("/turns/{code}")
    public Optional<Turn> one(@PathVariable String code) {
        return turnService.findById(code);
    }

    @GetMapping("/turns/{queue}")
    public List<Turn> inQueue(@RequestBody Queue queue) {
        return turnService.findByQueue(queue);
    }

    @GetMapping("/turns/{queue}/next")
    public Optional<Turn> nextInQueue(@RequestBody Queue queue) {
        return turnService.findNextTurnInQueue(queue);
    }

}
