package edu.eci.arsw.digitalqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.service.QueueService;

@RestController
public class QueueController {

    @Autowired
    QueueService queueService;

    @GetMapping("/queues")
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(queueService.all(), HttpStatus.OK);
    }

    @PostMapping("/queues")
    public ResponseEntity<?> newQueue(@RequestBody Queue newQueue){
        
        //System.out.println(newQueue.toString());
        queueService.newQueue(newQueue);
        /*
        System.out.println(newQueue.getId());
        
        if (queueService.findById(newQueue.getId()) == null){
            System.out.println("entro");
            queueService.newQueue(newQueue);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }*/
        return new ResponseEntity<>(HttpStatus.CREATED);
        //return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/queues/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(queueService.findById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/queues/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        queueService.findByName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/queues/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable String name){
        queueService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
