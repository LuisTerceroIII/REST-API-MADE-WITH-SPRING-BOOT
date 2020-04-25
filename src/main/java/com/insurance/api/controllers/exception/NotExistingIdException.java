package com.insurance.api.controllers.exception;

public class NotExistingIdException extends RuntimeException {

    public NotExistingIdException(Long id) {
        super("Zero matches for id : " + id);
    }
}
