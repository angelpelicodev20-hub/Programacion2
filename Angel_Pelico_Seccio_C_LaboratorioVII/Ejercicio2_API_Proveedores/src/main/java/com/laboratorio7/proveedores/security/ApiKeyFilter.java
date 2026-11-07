package com.laboratorio7.proveedores.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratorio7.proveedores.dto.ApiError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Filtro que protege los endpoints de /api/** con una API Key.
 * Se ejecuta antes de llegar al controlador: si el encabezado X-API-KEY
 * no viene o no coincide con la clave configurada, responde 401 y corta la petición.
 */
@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    public static final String HEADER_API_KEY = "X-API-KEY";
    private static final String RUTA_PROTEGIDA = "/api/";

    private final byte[] apiKeyEsperada;
    private final ObjectMapper objectMapper;

    public ApiKeyFilter(@Value("${api.key}") String apiKey, ObjectMapper objectMapper) {
        this.apiKeyEsperada = apiKey.getBytes(StandardCharsets.UTF_8);
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith(RUTA_PROTEGIDA);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String apiKeyRecibida = request.getHeader(HEADER_API_KEY);

        if (apiKeyRecibida == null || apiKeyRecibida.isBlank()) {
            rechazar(request, response, "Acceso denegado: falta el encabezado " + HEADER_API_KEY);
            return;
        }

        if (!esValida(apiKeyRecibida)) {
            rechazar(request, response, "Acceso denegado: la API Key es inválida");
            return;
        }

        filterChain.doFilter(request, response);
    }

    /** Comparación en tiempo constante para no filtrar información por el tiempo de respuesta. */
    private boolean esValida(String apiKeyRecibida) {
        return MessageDigest.isEqual(apiKeyRecibida.getBytes(StandardCharsets.UTF_8), apiKeyEsperada);
    }

    private void rechazar(HttpServletRequest request, HttpServletResponse response, String mensaje) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        objectMapper.writeValue(response.getWriter(),
                ApiError.of(HttpStatus.UNAUTHORIZED, mensaje, request.getRequestURI()));
    }
}
