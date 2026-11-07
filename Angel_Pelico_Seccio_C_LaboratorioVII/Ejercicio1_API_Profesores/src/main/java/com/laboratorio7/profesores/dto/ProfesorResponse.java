package com.laboratorio7.profesores.dto;

import com.laboratorio7.profesores.model.Estado;

/**
 * Datos de salida de un profesor que devuelve la API.
 */
public record ProfesorResponse(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String especialidad,
        Estado estado
) {
}
