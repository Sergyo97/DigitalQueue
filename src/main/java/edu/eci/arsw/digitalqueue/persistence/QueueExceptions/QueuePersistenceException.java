package edu.eci.arsw.digitalqueue.persistence.QueueExceptions;

public class QueuePersistenceException extends Exception {

    private static final long serialVersionUID = 1L;

    public QueuePersistenceException(String message) {
        super(message);
    }

    public QueuePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
