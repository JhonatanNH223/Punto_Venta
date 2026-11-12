package com.PV.Punto_Venta.exception;

public class DuplicateException extends RuntimeException {

    private final String entity;
    private final Object value;

    public DuplicateException(String  entity, Object value) {
        super(entity + " already exists: " + value);
        this.entity = entity;
        this.value = value;
    }

    public String getEntity() { return entity; }
    public Object getValue() { return value; }
}
