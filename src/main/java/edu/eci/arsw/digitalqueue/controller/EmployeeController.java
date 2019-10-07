package edu.eci.arsw.digitalqueue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.digitalqueue.model.Employee;
import edu.eci.arsw.digitalqueue.service.EmployeeService;

@CrossOrigin
@RestController
public class EmployeeController{

    @Autowired 
    private
    EmployeeService employeeService;


    @GetMapping("/employees")
    List<Employee> all(){
        return employeeService.findAll();
    }

    @PostMapping("/employees")
    public Employee create(@RequestBody Employee newEmployee){
        return employeeService.create(newEmployee);
    }
    
    @GetMapping("/employees/{id}")
    public Optional<Employee> one(@PathVariable long id){
        return employeeService.findById(id);
    }

    @GetMapping("/employees/{email}")
    public Optional<Employee> employeeByEmail(@PathVariable String email){
        return employeeService.findByEmail(email);
    }





}


