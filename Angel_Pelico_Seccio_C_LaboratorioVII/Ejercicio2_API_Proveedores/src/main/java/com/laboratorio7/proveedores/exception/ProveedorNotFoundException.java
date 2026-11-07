package com.laboratorio7.proveedores.exception;

/**
 * Se lanza cuando se consulta un proveedor que no existe (HTTP 404).
 */
public class ProveedorNotFoundException extends RuntimeException {

    public ProveedorNotFoundException(Long id) {
        super("No se encontró el proveedor con id " + id);
    }
}
