package edu.eci.arsw.digitalqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import edu.eci.arsw.digitalqueue.model.AttentionPoint;
import edu.eci.arsw.digitalqueue.service.AttentionPointService;

import java.util.List;



@RestController
public class AttentionPointController{

    @Autowired
    private AttentionPointService attPointService;   
    
    @GetMapping("/attentionPoints")
    List<AttentionPoint> all(){
        return attPointService.findAll();
    }

    @PostMapping("/attentionPoints")
    public boolean create(@RequestBody AttentionPoint attPoint){
        return attPointService.create(attPoint);
    }

    @GetMapping("/attentionPoints/{id}")
    public AttentionPoint one(@PathVariable Long id){
        return attPointService.findById(id).get();
    }

    @DeleteMapping("/attentionPoints/{id}")
    public boolean delete(@PathVariable Long id){
        AttentionPoint attPoint = attPointService.findById(id).get();
        attPointService.deleteAttentionPoint(attPoint);
        return true;
    }

    


    
}