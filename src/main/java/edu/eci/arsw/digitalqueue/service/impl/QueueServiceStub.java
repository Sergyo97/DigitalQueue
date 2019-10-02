package edu.eci.arsw.digitalqueue.service.impl;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.persistence.QueueExceptions.QueueNotFoundException;
import edu.eci.arsw.digitalqueue.persistence.QueueExceptions.QueuePersistenceException;
import edu.eci.arsw.digitalqueue.persistence.QueueRepository;
import edu.eci.arsw.digitalqueue.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class QueueServiceStub implements QueueService {

    @Autowired
    QueueRepository queueRepository;

    @Override
    public void newQueue(Queue newQueue) throws QueuePersistenceException {
        queueRepository.newQueue(newQueue);
    }

    @Override
    public Queue findByName(String name) throws QueueNotFoundException {
        return queueRepository.findByName(name);
    }

    @Override
    public Optional<Queue> findById(Long id) throws QueueNotFoundException{
        return queueRepository.findById(id);
    }

    @Override
    public void updateByName(String name) throws QueuePersistenceException{
        queueRepository.updateByName(name);
    }

    @Override
    public void deleteByName(String name) throws QueuePersistenceException{
        queueRepository.deleteByName(name);
    }
}
