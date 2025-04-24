package com.maxiamikel.crud_user_api.exception;

public class ObjectIdNotFoundException extends RuntimeException {
    public ObjectIdNotFoundException(String message) {
        super(message);
    }
}
