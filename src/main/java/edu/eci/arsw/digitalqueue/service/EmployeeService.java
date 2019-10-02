package edu.eci.arsw.digitalqueue.service;

import org.springframework.stereotype.Service;

import edu.eci.arsw.digitalqueue.model.Employee;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService{

    List<Employee> findAll();

    Employee create(Employee newEmployee);
    
    Optional<Employee> findById(Long Code);

    Optional<Employee> findByEmail(String email);

    
}