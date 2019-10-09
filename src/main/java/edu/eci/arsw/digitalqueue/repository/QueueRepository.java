package edu.eci.arsw.digitalqueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.digitalqueue.model.Queue;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {

    Queue findByName(String name);

}
