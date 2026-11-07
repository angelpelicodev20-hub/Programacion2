package com.laboratorio7.empleados.dto;

import com.laboratorio7.empleados.model.Estado;

/**
 * Datos de salida de un empleado que devuelve la API.
 */
public record EmpleadoResponse(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String especialidad,
        Estado estado
) {
}
