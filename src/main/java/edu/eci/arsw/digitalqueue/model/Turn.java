package edu.eci.arsw.digitalqueue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Turn {

    private @Id @GeneratedValue Long id;
    private String code;
    private Timestamp requestedDateTime;
    private Timestamp attendedDateTime;
    private Queue queue;
    private AttentionPoint attentionPoint;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(Timestamp requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public Timestamp getAttendedDateTime() {
        return attendedDateTime;
    }

    public void setAttendedDateTime(Timestamp attendedDateTime) {
        this.attendedDateTime = attendedDateTime;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public AttentionPoint getAttentionPoint() {
        return attentionPoint;
    }

    public void setAttentionPoint(AttentionPoint attentionPoint) {
        this.attentionPoint = attentionPoint;
    }

}
