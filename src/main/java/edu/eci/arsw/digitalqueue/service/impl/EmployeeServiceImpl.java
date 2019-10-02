
package edu.eci.arsw.digitalqueue.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.digitalqueue.model.Employee;
import edu.eci.arsw.digitalqueue.persistence.EmployeeRepository;
import edu.eci.arsw.digitalqueue.service.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {        
        return employeeRepository.findAll();
    }

    @Override
    public Employee create(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

}