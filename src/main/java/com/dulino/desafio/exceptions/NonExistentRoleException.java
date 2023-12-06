package com.dulino.desafio.exceptions;

public class NonExistentRoleException extends RuntimeException {
    public NonExistentRoleException(String message) {
        super(message);
    }
}
