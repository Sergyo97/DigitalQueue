package edu.eci.arsw.digitalqueue.service.impl;

import edu.eci.arsw.digitalqueue.service.TurnService;
import edu.eci.arsw.digitalqueue.model.Turn;
import edu.eci.arsw.digitalqueue.persistence.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TurnServiceStub implements TurnService {

    @Autowired
    TurnRepository turnRepository;

    @Override
    public Optional<Turn> findById(Long id) {
        return turnRepository.findById(id);
    }

    @Override
    public Turn findByCode(String code) {
        return turnRepository.findByCode(code);
    }

    @Override
    public void newTurn(Turn newTurn) {
        turnRepository.save(newTurn);
    }
}
