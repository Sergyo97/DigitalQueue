package edu.eci.arsw.digitalqueue.service;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.persistence.QueueExceptions.QueueNotFoundException;
import edu.eci.arsw.digitalqueue.persistence.QueueExceptions.QueuePersistenceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface QueueService {

    void newQueue(Queue newQueue) throws QueuePersistenceException;

    Queue findByName(String name) throws QueueNotFoundException;

    Optional<Queue> findById(Long id) throws QueueNotFoundException;

    void updateByName(String name) throws QueuePersistenceException;

    void deleteByName(String name) throws QueuePersistenceException;

}
