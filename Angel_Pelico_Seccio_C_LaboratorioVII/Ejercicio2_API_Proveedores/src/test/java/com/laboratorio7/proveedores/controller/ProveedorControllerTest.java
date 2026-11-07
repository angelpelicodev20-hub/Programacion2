package com.laboratorio7.proveedores.controller;

import com.laboratorio7.proveedores.dto.ProveedorResponse;
import com.laboratorio7.proveedores.exception.ProveedorNotFoundException;
import com.laboratorio7.proveedores.model.Estado;
import com.laboratorio7.proveedores.security.ApiKeyFilter;
import com.laboratorio7.proveedores.service.ProveedorService;
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
@WebMvcTest(ProveedorController.class)
class ProveedorControllerTest {

    private static final String URL = "/api/proveedores";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProveedorService proveedorService;

    @Value("${api.key}")
    private String apiKey;

    private final ProveedorResponse proveedor = new ProveedorResponse(
            1L, "Ana", "López", "ana.lopez@empresa.com", "Tecnología", Estado.ACTIVO);

    @Test
    @DisplayName("POST /api/proveedores con datos válidos retorna 201 Created")
    void crearProveedor_retorna201() throws Exception {
        when(proveedorService.crearProveedor(any())).thenReturn(proveedor);

        String json = """
                {"nombre": "Ana", "apellido": "López", "correo": "ana.lopez@empresa.com",
                 "categoria": "Tecnología", "estado": "ACTIVO"}
                """;

        mockMvc.perform(post(URL).header(ApiKeyFilter.HEADER_API_KEY, apiKey)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/api/proveedores/1")))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }

    @Test
    @DisplayName("GET /api/proveedores/{id} existente retorna 200 OK")
    void obtenerProveedorExistente_retorna200() throws Exception {
        when(proveedorService.obtenerProveedorPorId(1L)).thenReturn(proveedor);

        mockMvc.perform(get(URL + "/1").header(ApiKeyFilter.HEADER_API_KEY, apiKey))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.correo").value("ana.lopez@empresa.com"));
    }

    @Test
    @DisplayName("GET /api/proveedores/{id} inexistente retorna 404 Not Found")
    void obtenerProveedorInexistente_retorna404() throws Exception {
        when(proveedorService.obtenerProveedorPorId(99L)).thenThrow(new ProveedorNotFoundException(99L));

        mockMvc.perform(get(URL + "/99").header(ApiKeyFilter.HEADER_API_KEY, apiKey))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.mensaje").value("No se encontró el proveedor con id 99"));
    }

    @Test
    @DisplayName("GET /api/proveedores?categoria= retorna 200 con la lista filtrada")
    void listarProveedoresPorCategoria_retorna200() throws Exception {
        when(proveedorService.listarProveedores("Tecnología")).thenReturn(List.of(proveedor));

        mockMvc.perform(get(URL).param("categoria", "Tecnología")
                        .header(ApiKeyFilter.HEADER_API_KEY, apiKey))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].categoria").value("Tecnología"));
    }

    @Test
    @DisplayName("POST con nombre/apellido vacíos y correo inválido retorna 400 Bad Request")
    void crearProveedorConDatosInvalidos_retorna400() throws Exception {
        String json = """
                {"nombre": "", "apellido": "  ", "correo": "correo-invalido", "categoria": "Tecnología"}
                """;

        mockMvc.perform(post(URL).header(ApiKeyFilter.HEADER_API_KEY, apiKey)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errores.nombre").value("El nombre no puede estar vacío"))
                .andExpect(jsonPath("$.errores.apellido").value("El apellido no puede estar vacío"))
                .andExpect(jsonPath("$.errores.correo").value("El correo no tiene un formato válido"));

        verify(proveedorService, never()).crearProveedor(any());
    }

    @Test
    @DisplayName("Petición sin API Key retorna 401 Unauthorized")
    void peticionSinApiKey_retorna401() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensaje").value("Acceso denegado: falta el encabezado X-API-KEY"));

        verify(proveedorService, never()).listarProveedores(any());
    }

    @Test
    @DisplayName("Petición con API Key inválida retorna 401 Unauthorized")
    void peticionConApiKeyInvalida_retorna401() throws Exception {
        mockMvc.perform(get(URL).header(ApiKeyFilter.HEADER_API_KEY, "clave-incorrecta"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensaje").value("Acceso denegado: la API Key es inválida"));
    }
}
