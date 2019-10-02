package edu.eci.arsw.digitalqueue.persistence;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.persistence.QueueExceptions.QueueNotFoundException;
import edu.eci.arsw.digitalqueue.persistence.QueueExceptions.QueuePersistenceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {

    Queue findByName(String name) throws QueueNotFoundException;

    Optional<Queue> findById(Long id);

    void newQueue(Queue newQueue) throws QueuePersistenceException;

    void updateByName(String name) throws QueuePersistenceException;

    void deleteByName(String name) throws QueuePersistenceException;

}
