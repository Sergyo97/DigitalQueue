package edu.eci.arsw.digitalqueue.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    @ManyToMany
    private Set<Role> roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public AttentionPoint getAttentionPoint() {
        return attentionPoint;
    }

    public void setAttentionPoint(AttentionPoint attentionPoint) {
        this.attentionPoint = attentionPoint;
    }
}