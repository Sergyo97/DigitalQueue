package edu.eci.arsw.digitalqueue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.digitalqueue.model.AttentionPoint;
import edu.eci.arsw.digitalqueue.model.Queue;

@Repository
public interface AttentionPointRepository extends JpaRepository<AttentionPoint, Long>{

    List<AttentionPoint> findByEnable(Boolean enable);

    List<AttentionPoint> findByQueue(Queue queue);

}