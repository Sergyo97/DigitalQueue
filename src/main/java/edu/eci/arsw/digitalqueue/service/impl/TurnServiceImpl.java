package edu.eci.arsw.digitalqueue.service.impl;

import edu.eci.arsw.digitalqueue.model.AttentionPoint;
import edu.eci.arsw.digitalqueue.service.TurnService;
import edu.eci.arsw.digitalqueue.model.Turn;
import edu.eci.arsw.digitalqueue.persistence.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TurnServiceImpl implements TurnService {

    @Autowired
    TurnRepository turnRepository;

    @Override
    public Turn findByCode(String code) {
        return turnRepository.findByCode(code);
    }

    @Override
    public void newTurn(Turn newTurn) {
        turnRepository.save(newTurn);
    }

    @Override
    public void cancelTurn(Turn turn) {
        // TODO: Implement
    }

    @Override
    public void attendTurn(AttentionPoint attentionPoint, Turn turn) {
        turn.setAttended(true);
        turn.setAttentionPoint(attentionPoint);
        // TODO: Finish
    }
}
