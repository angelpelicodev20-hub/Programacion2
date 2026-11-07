package com.laboratorio7.empleados.exception;

/**
 * Se lanza cuando se consulta un empleado que no existe (HTTP 404).
 */
public class EmpleadoNotFoundException extends RuntimeException {

    public EmpleadoNotFoundException(Long id) {
        super("No se encontró el empleado con id " + id);
    }
}
