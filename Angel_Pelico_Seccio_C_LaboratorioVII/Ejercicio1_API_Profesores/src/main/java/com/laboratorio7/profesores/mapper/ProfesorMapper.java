package com.laboratorio7.profesores.mapper;

import com.laboratorio7.profesores.dto.ProfesorRequest;
import com.laboratorio7.profesores.dto.ProfesorResponse;
import com.laboratorio7.profesores.model.Estado;
import com.laboratorio7.profesores.model.Profesor;

import java.util.Locale;

/**
 * Convierte entre la entidad {@link Profesor} y sus DTOs.
 */
public final class ProfesorMapper {

    private ProfesorMapper() {
    }

    public static Profesor toEntity(ProfesorRequest request) {
        Profesor profesor = new Profesor();
        profesor.setNombre(request.nombre().trim());
        profesor.setApellido(request.apellido().trim());
        profesor.setCorreo(request.correo().trim().toLowerCase(Locale.ROOT));
        profesor.setEspecialidad(request.especialidad().trim());
        profesor.setEstado(request.estado() != null ? request.estado() : Estado.ACTIVO);
        return profesor;
    }

    public static ProfesorResponse toResponse(Profesor profesor) {
        return new ProfesorResponse(
                profesor.getId(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getCorreo(),
                profesor.getEspecialidad(),
                profesor.getEstado()
        );
    }
}
