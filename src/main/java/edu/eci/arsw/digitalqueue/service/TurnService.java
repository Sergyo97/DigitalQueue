package edu.eci.arsw.digitalqueue.service;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.model.Turn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TurnService {

    List<Turn> findAll();

    Turn create(Turn newTurn);

    Optional<Turn> findById(String code);

    Optional<Turn> findNextTurnInQueue(Queue queue);

    List<Turn> findByQueue(Queue queue);
}
