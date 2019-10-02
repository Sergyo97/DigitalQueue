package edu.eci.arsw.digitalqueue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.eci.arsw.digitalqueue.model.Queue;

@Service
public interface QueueService {

    void newQueue(Queue newQueue);

    Queue findByName(String name);

    Optional<Queue> findById(Long id);

    void updateByName(String name);

    void deleteByName(String name);

	List<Queue> all();

}
