package com.laboratorio7.profesores.exception;

import com.laboratorio7.profesores.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Traduce las excepciones a respuestas HTTP con el formato {@link ApiError}.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** 404: el profesor consultado no existe. */
    @ExceptionHandler(ProfesorNotFoundException.class)
    public ResponseEntity<ApiError> manejarNoEncontrado(ProfesorNotFoundException ex, HttpServletRequest request) {
        return construir(HttpStatus.NOT_FOUND, ex.getMessage(), request, null);
    }

    /** 409: ya existe un profesor con ese correo. */
    @ExceptionHandler(CorreoDuplicadoException.class)
    public ResponseEntity<ApiError> manejarCorreoDuplicado(CorreoDuplicadoException ex, HttpServletRequest request) {
        return construir(HttpStatus.CONFLICT, ex.getMessage(), request, null);
    }

    /** 400: fallaron las validaciones del cuerpo (nombre vacío, correo inválido, ...). */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> manejarValidaciones(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errores.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return construir(HttpStatus.BAD_REQUEST, "La solicitud contiene datos inválidos", request, errores);
    }

    /** 400: el JSON está mal formado o el estado no es ACTIVO / INACTIVO. */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> manejarCuerpoIlegible(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return construir(HttpStatus.BAD_REQUEST,
                "El cuerpo de la solicitud no es válido. Verifique el formato JSON y que el estado sea ACTIVO o INACTIVO",
                request, null);
    }

    /** 400: un parámetro de la URL tiene un tipo incorrecto (ej. /api/profesores/abc). */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> manejarTipoIncorrecto(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String mensaje = String.format("El valor '%s' no es válido para el parámetro '%s'", ex.getValue(), ex.getName());
        return construir(HttpStatus.BAD_REQUEST, mensaje, request, null);
    }

    /**
     * Respaldo para el resto de errores. Las excepciones estándar de Spring MVC
     * (ruta inexistente, método no permitido, ...) conservan su código HTTP;
     * cualquier otra se responde como 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> manejarGeneral(Exception ex, HttpServletRequest request) {
        if (ex instanceof ErrorResponse errorResponse) {
            HttpStatus status = HttpStatus.valueOf(errorResponse.getStatusCode().value());
            return construir(status, ex.getMessage(), request, null);
        }
        log.error("Error inesperado al procesar {}", request.getRequestURI(), ex);
        return construir(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error interno en el servidor", request, null);
    }

    private ResponseEntity<ApiError> construir(HttpStatus status, String mensaje, HttpServletRequest request,
                                               Map<String, String> errores) {
        return ResponseEntity.status(status)
                .body(ApiError.of(status, mensaje, request.getRequestURI(), errores));
    }
}
