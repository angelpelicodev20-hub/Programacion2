package com.laboratorio7.profesores.controller;

import com.laboratorio7.profesores.dto.ProfesorRequest;
import com.laboratorio7.profesores.dto.ProfesorResponse;
import com.laboratorio7.profesores.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Endpoints REST para administrar profesores.
 * Todos requieren el encabezado X-API-KEY.
 */
@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    /** POST /api/profesores -> 201 Created */
    @PostMapping
    public ResponseEntity<ProfesorResponse> crearProfesor(@Valid @RequestBody ProfesorRequest request) {
        ProfesorResponse creado = profesorService.crearProfesor(request);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.id())
                .toUri();
        return ResponseEntity.created(ubicacion).body(creado);
    }

    /** GET /api/profesores y GET /api/profesores?especialidad=... -> 200 OK */
    @GetMapping
    public ResponseEntity<List<ProfesorResponse>> listarProfesores(
            @RequestParam(name = "especialidad", required = false) String especialidad) {
        return ResponseEntity.ok(profesorService.listarProfesores(especialidad));
    }

    /** GET /api/profesores/{id} -> 200 OK o 404 Not Found */
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponse> obtenerProfesorPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profesorService.obtenerProfesorPorId(id));
    }
}
