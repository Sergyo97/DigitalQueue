package edu.eci.arsw.digitalqueue.exception;

public class QueueNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public QueueNotFoundException(Long id) {
        super("Could not find queue " + id);
    }

}
