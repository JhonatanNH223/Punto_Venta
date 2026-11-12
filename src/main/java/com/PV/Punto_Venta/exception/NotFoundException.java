package com.PV.Punto_Venta.exception;

public class NotFoundException extends RuntimeException {

    private final String entity;
    private final Object value;

    public NotFoundException(String entity,Object value) {
        super(entity + " Not Found: " + value);
        this.entity = entity;
        this.value = value;
    }

    public String getEntity() { return entity; }
    public Object getValue() { return value; }

}
