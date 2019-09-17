package edu.eci.arsw.digitalqueue.service;

import edu.eci.arsw.digitalqueue.model.AttentionPoint;
import edu.eci.arsw.digitalqueue.model.Turn;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TurnService {

    Turn findByCode(String code);

    void newTurn(Turn newTurn);

    void cancelTurn(Turn turn);

    void attendTurn(AttentionPoint attentionPoint, Turn turn);

}
