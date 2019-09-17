package edu.eci.arsw.digitalqueue.controller;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class QueueController {

    @Autowired
    QueueService queueService;

    @PostMapping("/queues")
    public ResponseEntity<?> newQueue(@RequestBody Queue newQueue){
        if (queueService.findById(newQueue.getId()) == null){
            queueService.newQueue(newQueue);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/queues/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(queueService.findById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/queues/{id}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        queueService.findByName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/queues/{id}")
    public ResponseEntity<?> deleteByName(@PathVariable String name){
        queueService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
