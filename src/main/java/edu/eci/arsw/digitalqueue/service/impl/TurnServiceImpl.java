package edu.eci.arsw.digitalqueue.service.impl;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.service.TurnService;
import edu.eci.arsw.digitalqueue.model.Turn;
import edu.eci.arsw.digitalqueue.persistence.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TurnServiceImpl implements TurnService {

    @Autowired
    private TurnRepository turnRepository;

    @Override
    public List<Turn> findAll() {
        return turnRepository.findAll();
    }

    @Override
    public Turn create(Turn newTurn) {
        return turnRepository.save(newTurn);
    }

    @Override
    public Optional<Turn> findByCode(String code) {
        return turnRepository.findByCode(code);
    }

    @Override
    public Optional<Turn> findNextTurnInQueue(Queue queue) {
        return turnRepository.findFirstByQueueAndAttendedFalseOrderByRequestedDateTimeDesc(queue);
    }

    @Override
    public List<Turn> findByQueue(Queue queue) {
        return turnRepository.findByQueue(queue);
    }
}
