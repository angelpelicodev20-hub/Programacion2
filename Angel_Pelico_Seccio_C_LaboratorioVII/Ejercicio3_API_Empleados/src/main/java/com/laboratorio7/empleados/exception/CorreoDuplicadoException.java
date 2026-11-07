package com.laboratorio7.empleados.exception;

/**
 * Se lanza cuando se intenta registrar un correo que ya existe (HTTP 409).
 */
public class CorreoDuplicadoException extends RuntimeException {

    public CorreoDuplicadoException(String correo) {
        super("Ya existe un empleado registrado con el correo " + correo);
    }
}
