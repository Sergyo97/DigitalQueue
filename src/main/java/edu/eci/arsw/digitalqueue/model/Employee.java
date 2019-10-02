package edu.eci.arsw.digitalqueue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee{


    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String email;
    @OneToOne(mappedBy = "employee")
    private AttentionPoint attentionPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AttentionPoint getAttentionPoint() {
        return attentionPoint;
    }

    public void setAttentionPoint(AttentionPoint attentionPoint) {
        this.attentionPoint = attentionPoint;
    }
}