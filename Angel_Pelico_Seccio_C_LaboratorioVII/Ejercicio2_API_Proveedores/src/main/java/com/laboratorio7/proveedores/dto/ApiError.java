package com.laboratorio7.proveedores.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Formato estándar de las respuestas de error de la API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        String mensaje,
        String ruta,
        Map<String, String> errores
) {

    public static ApiError of(HttpStatus status, String mensaje, String ruta) {
        return of(status, mensaje, ruta, null);
    }

    public static ApiError of(HttpStatus status, String mensaje, String ruta, Map<String, String> errores) {
        return new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(), mensaje, ruta, errores);
    }
}
