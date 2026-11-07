package com.laboratorio7.profesores.exception;

/**
 * Se lanza cuando se consulta un profesor que no existe (HTTP 404).
 */
public class ProfesorNotFoundException extends RuntimeException {

    public ProfesorNotFoundException(Long id) {
        super("No se encontró el profesor con id " + id);
    }
}
