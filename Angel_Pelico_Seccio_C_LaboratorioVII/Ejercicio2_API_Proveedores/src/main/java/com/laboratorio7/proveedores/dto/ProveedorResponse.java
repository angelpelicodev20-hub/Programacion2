package com.laboratorio7.proveedores.dto;

import com.laboratorio7.proveedores.model.Estado;

/**
 * Datos de salida de un proveedor que devuelve la API.
 */
public record ProveedorResponse(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String categoria,
        Estado estado
) {
}
