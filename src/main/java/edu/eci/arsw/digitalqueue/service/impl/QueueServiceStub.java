package edu.eci.arsw.digitalqueue.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.persistence.QueueRepository;
import edu.eci.arsw.digitalqueue.service.QueueService;

@Component
public class QueueServiceStub implements QueueService {

    @Autowired
    QueueRepository queueRepository;

    @Override
    public void newQueue(Queue newQueue)  {
        queueRepository.save(newQueue);
    }

    @Override
    public Queue findByName(String name) {
        return queueRepository.findByName(name);
    }

    @Override
    public Optional<Queue> findById(Long id){
        return queueRepository.findById(id);
    }

    @Override
    public void updateByName(String name) {
        queueRepository.save(queueRepository.findByName(name));
    }

    @Override
    public void deleteByName(String name) {
        queueRepository.delete(queueRepository.findByName(name));
    }
}
