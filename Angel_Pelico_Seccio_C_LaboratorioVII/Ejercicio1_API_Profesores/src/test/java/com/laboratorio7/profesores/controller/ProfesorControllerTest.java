package com.laboratorio7.profesores.controller;

import com.laboratorio7.profesores.dto.ProfesorResponse;
import com.laboratorio7.profesores.exception.ProfesorNotFoundException;
import com.laboratorio7.profesores.model.Estado;
import com.laboratorio7.profesores.security.ApiKeyFilter;
import com.laboratorio7.profesores.service.ProfesorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Pruebas de la capa web: códigos HTTP, validaciones, manejo de errores y API Key.
 * El servicio se simula con Mockito.
 */
@WebMvcTest(ProfesorController.class)
class ProfesorControllerTest {

    private static final String URL = "/api/profesores";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProfesorService profesorService;

    @Value("${api.key}")
    private String apiKey;

    private final ProfesorResponse profesor = new ProfesorResponse(
            1L, "Ana", "López", "ana.lopez@universidad.edu", "Matemáticas", Estado.ACTIVO);

    @Test
    @DisplayName("POST /api/profesores con datos válidos retorna 201 Created")
    void crearProfesor_retorna201() throws Exception {
        when(profesorService.crearProfesor(any())).thenReturn(profesor);

        String json = """
                {"nombre": "Ana", "apellido": "López", "correo": "ana.lopez@universidad.edu",
                 "especialidad": "Matemáticas", "estado": "ACTIVO"}
                """;

        mockMvc.perform(post(URL).header(ApiKeyFilter.HEADER_API_KEY, apiKey)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/api/profesores/1")))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }

    @Test
    @DisplayName("GET /api/profesores/{id} existente retorna 200 OK")
    void obtenerProfesorExistente_retorna200() throws Exception {
        when(profesorService.obtenerProfesorPorId(1L)).thenReturn(profesor);

        mockMvc.perform(get(URL + "/1").header(ApiKeyFilter.HEADER_API_KEY, apiKey))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.correo").value("ana.lopez@universidad.edu"));
    }

    @Test
    @DisplayName("GET /api/profesores/{id} inexistente retorna 404 Not Found")
    void obtenerProfesorInexistente_retorna404() throws Exception {
        when(profesorService.obtenerProfesorPorId(99L)).thenThrow(new ProfesorNotFoundException(99L));

        mockMvc.perform(get(URL + "/99").header(ApiKeyFilter.HEADER_API_KEY, apiKey))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.mensaje").value("No se encontró el profesor con id 99"));
    }

    @Test
    @DisplayName("GET /api/profesores?especialidad= retorna 200 con la lista filtrada")
    void listarProfesoresPorEspecialidad_retorna200() throws Exception {
        when(profesorService.listarProfesores("Matemáticas")).thenReturn(List.of(profesor));

        mockMvc.perform(get(URL).param("especialidad", "Matemáticas")
                        .header(ApiKeyFilter.HEADER_API_KEY, apiKey))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].especialidad").value("Matemáticas"));
    }

    @Test
    @DisplayName("POST con nombre/apellido vacíos y correo inválido retorna 400 Bad Request")
    void crearProfesorConDatosInvalidos_retorna400() throws Exception {
        String json = """
                {"nombre": "", "apellido": "  ", "correo": "correo-invalido", "especialidad": "Matemáticas"}
                """;

        mockMvc.perform(post(URL).header(ApiKeyFilter.HEADER_API_KEY, apiKey)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errores.nombre").value("El nombre no puede estar vacío"))
                .andExpect(jsonPath("$.errores.apellido").value("El apellido no puede estar vacío"))
                .andExpect(jsonPath("$.errores.correo").value("El correo no tiene un formato válido"));

        verify(profesorService, never()).crearProfesor(any());
    }

    @Test
    @DisplayName("Petición sin API Key retorna 401 Unauthorized")
    void peticionSinApiKey_retorna401() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensaje").value("Acceso denegado: falta el encabezado X-API-KEY"));

        verify(profesorService, never()).listarProfesores(any());
    }

    @Test
    @DisplayName("Petición con API Key inválida retorna 401 Unauthorized")
    void peticionConApiKeyInvalida_retorna401() throws Exception {
        mockMvc.perform(get(URL).header(ApiKeyFilter.HEADER_API_KEY, "clave-incorrecta"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensaje").value("Acceso denegado: la API Key es inválida"));
    }
}
