package edu.eci.arsw.digitalqueue.exception;

public class TurnNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public TurnNotFoundException(String id) {
        super("Could not find turn " + id);
    }

}
