package edu.eci.arsw.digitalqueue.controller;

import edu.eci.arsw.digitalqueue.model.Turn;
import edu.eci.arsw.digitalqueue.service.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TurnController {

    @Autowired
    TurnService turnService;

    @PostMapping("/turns")
    public ResponseEntity<?> newTurn (@RequestBody Turn newTurn) {
        if (turnService.findByCode(newTurn.getCode()) == null) {
            turnService.newTurn(newTurn);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
