package com.PV.Punto_Venta.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String entity, String data, String problem) {
        super(entity + ": " + data + " cannot be " + problem);
    }
}
