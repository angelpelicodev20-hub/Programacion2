package com.laboratorio7.empleados.dto;

import com.laboratorio7.empleados.model.Estado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Datos de entrada para crear un empleado.
 * Si no se envía el estado, el empleado se crea como ACTIVO.
 */
public record EmpleadoRequest(

        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        String apellido,

        @NotBlank(message = "El correo es obligatorio")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "El correo no tiene un formato válido")
        String correo,

        @NotBlank(message = "La especialidad es obligatoria")
        String especialidad,

        Estado estado
) {
}
