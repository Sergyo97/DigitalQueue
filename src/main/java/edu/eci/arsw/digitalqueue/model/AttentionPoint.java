package edu.eci.arsw.digitalqueue.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attention_points")
public class AttentionPoint {

    private @Id @GeneratedValue Long id;
    private String code;
    private Boolean enable;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private User employee;
    @OneToMany(mappedBy = "attentionPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turn> turns;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id")
    private Queue queue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

}
