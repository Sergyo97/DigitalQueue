package edu.eci.arsw.digitalqueue.exception;

public class QueueNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueNotFoundException(String name) {
        super("Could not find queue " + name);
    }

}
