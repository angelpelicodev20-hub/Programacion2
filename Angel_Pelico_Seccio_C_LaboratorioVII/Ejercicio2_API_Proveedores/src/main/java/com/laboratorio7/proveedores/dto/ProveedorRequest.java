package com.laboratorio7.proveedores.dto;

import com.laboratorio7.proveedores.model.Estado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Datos de entrada para crear un proveedor.
 * Si no se envía el estado, el proveedor se crea como ACTIVO.
 */
public record ProveedorRequest(

        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        String apellido,

        @NotBlank(message = "El correo es obligatorio")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "El correo no tiene un formato válido")
        String correo,

        @NotBlank(message = "La categoría es obligatoria")
        String categoria,

        Estado estado
) {
}
