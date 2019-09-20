package edu.eci.arsw.digitalqueue.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.digitalqueue.model.AttentionPoint;

@Repository
public interface AttentionPointRepository extends JpaRepository<AttentionPoint, Long>{

    Optional<AttentionPoint> findById(Long id);

    void updateAttentionPoint(AttentionPoint newAttentionPoint);

    List<AttentionPoint> findEnableAttentionPoint();

    List<AttentionPoint> findDisaableAttentionPoint();

}