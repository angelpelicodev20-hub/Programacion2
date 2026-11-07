package com.laboratorio7.empleados.mapper;

import com.laboratorio7.empleados.dto.EmpleadoRequest;
import com.laboratorio7.empleados.dto.EmpleadoResponse;
import com.laboratorio7.empleados.model.Estado;
import com.laboratorio7.empleados.model.Empleado;

import java.util.Locale;

/**
 * Convierte entre la entidad {@link Empleado} y sus DTOs.
 */
public final class EmpleadoMapper {

    private EmpleadoMapper() {
    }

    public static Empleado toEntity(EmpleadoRequest request) {
        Empleado empleado = new Empleado();
        empleado.setNombre(request.nombre().trim());
        empleado.setApellido(request.apellido().trim());
        empleado.setCorreo(request.correo().trim().toLowerCase(Locale.ROOT));
        empleado.setEspecialidad(request.especialidad().trim());
        empleado.setEstado(request.estado() != null ? request.estado() : Estado.ACTIVO);
        return empleado;
    }

    public static EmpleadoResponse toResponse(Empleado empleado) {
        return new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCorreo(),
                empleado.getEspecialidad(),
                empleado.getEstado()
        );
    }
}
