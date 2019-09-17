package edu.eci.arsw.digitalqueue.persistence.QueueExceptions;

public class QueueNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public QueueNotFoundException(String message) {
        super(message);
    }

    public QueueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
