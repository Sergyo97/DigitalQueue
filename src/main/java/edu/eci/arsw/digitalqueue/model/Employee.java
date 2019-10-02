package edu.eci.arsw.digitalqueue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("unusef")
@Entity
public class Employee{


    private @Id
    @GeneratedValue
    Long id;
}