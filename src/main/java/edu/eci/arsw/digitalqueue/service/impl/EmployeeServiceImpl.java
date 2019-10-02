
package edu.eci.arsw.digitalqueue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.digitalqueue.persistence.EmployeeRepository;
import edu.eci.arsw.digitalqueue.service.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

}