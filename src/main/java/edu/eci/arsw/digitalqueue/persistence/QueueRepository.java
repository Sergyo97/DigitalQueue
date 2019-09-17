package edu.eci.arsw.digitalqueue.persistence;

import edu.eci.arsw.digitalqueue.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {

    Queue findByName(String name);

    Optional<Queue> findById(Long id);

    void newQueue(Queue newQueue);

    void updateByName(String name);

    void deleteByName(String name);

}
