package edu.eci.arsw.digitalqueue.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "attentionPoint")
    private List<Turn> turns;
    @ManyToOne
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Queue getQueue(){
        return queue;
    }

    public void setQueue(Queue queue){
        this.queue = queue;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    public void addTurn(Turn turn){
        turns.add(turn);
    }

}
