package edu.eci.arsw.digitalqueue.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.digitalqueue.model.Employee;
import edu.eci.arsw.digitalqueue.model.Queue;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {





}