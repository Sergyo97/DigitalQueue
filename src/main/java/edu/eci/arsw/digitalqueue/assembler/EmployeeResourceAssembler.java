package edu.eci.arsw.digitalqueue.assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import edu.eci.arsw.digitalqueue.controller.EmployeeController;
import edu.eci.arsw.digitalqueue.model.Employee;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * EmployeeAssembler
 */
@Component
public class EmployeeResourceAssembler implements ResourceAssembler<Employee, Resource<Employee>> {

    @Override
    public Resource<Employee> toResource(Employee employee) {
        return new Resource<Employee>(employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("Employees"));
    }

}