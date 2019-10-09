package edu.eci.arsw.digitalqueue.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.eci.arsw.digitalqueue.exception.QueueNotFoundException;

/**
 * QueueNotFoundAdvice
 */
@ControllerAdvice
public class QueueNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(QueueNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    String queueNotFoundHandler(QueueNotFoundException exception){
        return exception.getMessage();
    }

}