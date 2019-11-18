package edu.eci.arsw.digitalqueue.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Queue {

    private @Id @GeneratedValue Long id;
    private String name;
    private String identifier;
    @OneToMany(mappedBy = "queue")
    private List<Turn> turns;
    private Boolean status;
    @OneToMany(mappedBy = "queue")
    private List<AttentionPoint> attentionPoints;

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

    public String getIdentifier(){
        return identifier;
    }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public Boolean getStatus(){
        return status;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

    /**
     * @return the attentionPoints
     */
    public List<AttentionPoint> getAttentionPoints() {
        return attentionPoints;
    }

    /**
     * @param attentionPoints the attentionPoints to set
     */
    public void setAttentionPoints(List<AttentionPoint> attentionPoints) {
        this.attentionPoints = attentionPoints;
    }

    /**
     * @return the turns
     */
    public List<Turn> getTurns() {
        return turns;
    }

    /**
     * @param turns the turns to set
     */
    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

}
