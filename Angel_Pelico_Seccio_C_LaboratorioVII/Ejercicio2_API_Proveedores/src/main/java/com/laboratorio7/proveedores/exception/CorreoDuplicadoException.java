package com.laboratorio7.proveedores.exception;

/**
 * Se lanza cuando se intenta registrar un correo que ya existe (HTTP 409).
 */
public class CorreoDuplicadoException extends RuntimeException {

    public CorreoDuplicadoException(String correo) {
        super("Ya existe un proveedor registrado con el correo " + correo);
    }
}
