package com.dulino.desafio.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String msg) {
        super(msg);
    }
}
