package edu.eci.arsw.digitalqueue.service;

import edu.eci.arsw.digitalqueue.model.Turn;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TurnService {

    Optional<Turn> findById(Long id);

    Turn findByCode(String code);

    void newTurn(Turn newTurn);

}
