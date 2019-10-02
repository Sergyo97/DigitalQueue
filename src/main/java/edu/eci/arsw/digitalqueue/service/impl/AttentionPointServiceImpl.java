
package edu.eci.arsw.digitalqueue.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.digitalqueue.model.AttentionPoint;
import edu.eci.arsw.digitalqueue.model.Employee;
import edu.eci.arsw.digitalqueue.persistence.AttentionPointRepository;
import edu.eci.arsw.digitalqueue.service.AttentionPointService;

@Component
public class AttentionPointServiceImpl implements AttentionPointService {

    @Autowired
    private AttentionPointRepository attentionPointRepository;

    @Override
    public List<AttentionPoint> findAll() {

        return attentionPointRepository.findAll();
    }

    @Override
    public boolean create(AttentionPoint newAttPoint) {
        
        attentionPointRepository.save(newAttPoint);
        if(findById(newAttPoint.getId()) != null ){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAttentionPoint(AttentionPoint attPoint) {        
        attentionPointRepository.delete(attPoint);
        if( findById(attPoint.getId()) != null){
            return false;
        } 
        return true;
    }

    @Override
    public Optional<AttentionPoint> findById(Long id) {
        return attentionPointRepository.findById(id);
    }

    @Override
    public List<AttentionPoint> findEnableAttentionPoints() {
        return attentionPointRepository.findEnableAttentionPoint(); 
    }

    @Override
    public List<AttentionPoint> findDisableAttentionPoints() {
        return attentionPointRepository.findDisaableAttentionPoint();
    }

    @Override
    public void assignEmployeToAttentionPoint(Long idAttPoint, Employee emp) {
        Optional<AttentionPoint> attPointOptional = findById(idAttPoint);
        AttentionPoint attPoint = attPointOptional.get();
        attPoint.setEmployee(emp);
        attentionPointRepository.updateAttentionPoint(attPoint);
        
    }



}