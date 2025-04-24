package com.maxiamikel.crud_user_api.exception;

public class DuplicationCreateUserEmailException extends RuntimeException {
    public DuplicationCreateUserEmailException(String message) {
        super(message);
    }
}
