package edu.eci.arsw.digitalqueue.service;

import edu.eci.arsw.digitalqueue.model.AttentionPoint;
import edu.eci.arsw.digitalqueue.model.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface AttentionPointService{

    List<AttentionPoint> findAll();

    boolean create(AttentionPoint newAttPoint);

    boolean deleteAttentionPoint(AttentionPoint attPoint);

    Optional<AttentionPoint> findById(Long id);

    List<AttentionPoint> findEnableAttentionPoints();

    List<AttentionPoint> findDisableAttentionPoints();

    void assignEmployeToAttentionPoint(Long idAttPoint, Employee emp);
    
}
