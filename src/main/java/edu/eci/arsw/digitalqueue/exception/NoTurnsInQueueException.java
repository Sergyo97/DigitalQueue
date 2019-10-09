package edu.eci.arsw.digitalqueue.exception;

public class NoTurnsInQueueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoTurnsInQueueException(String name) {
        super("Could not find turns in queue " + name);
    }
}
