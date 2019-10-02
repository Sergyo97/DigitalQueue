package edu.eci.arsw.digitalqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.service.EmployeeService;
import edu.eci.arsw.digitalqueue.service.QueueService;

@RestController
public class EmployeeController{

    @Autowired
    EmployeeService employeeService;





}


