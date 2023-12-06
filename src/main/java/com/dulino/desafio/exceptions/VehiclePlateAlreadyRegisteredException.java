package com.dulino.desafio.exceptions;

public class VehiclePlateAlreadyRegisteredException extends RuntimeException {
    public VehiclePlateAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
