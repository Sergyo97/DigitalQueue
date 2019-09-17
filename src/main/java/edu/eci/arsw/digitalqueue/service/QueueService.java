package edu.eci.arsw.digitalqueue.service;

import edu.eci.arsw.digitalqueue.model.Queue;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface QueueService {

    void newQueue(Queue newQueue);

    Queue findByName(String name);

    Optional<Queue> findById(Long id);

    void updateByName(String name);

    void deleteByName(String name);

}
