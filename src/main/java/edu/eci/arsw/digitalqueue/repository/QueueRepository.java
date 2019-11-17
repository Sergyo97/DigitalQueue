package edu.eci.arsw.digitalqueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.digitalqueue.model.Queue;

import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {

    Optional<Queue> findByName(String name);

}
