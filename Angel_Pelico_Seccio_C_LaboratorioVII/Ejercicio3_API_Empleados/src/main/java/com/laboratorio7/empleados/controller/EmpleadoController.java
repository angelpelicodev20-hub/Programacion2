package com.laboratorio7.empleados.controller;

import com.laboratorio7.empleados.dto.EmpleadoRequest;
import com.laboratorio7.empleados.dto.EmpleadoResponse;
import com.laboratorio7.empleados.service.EmpleadoService;
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
 * Endpoints REST para administrar empleados.
 * Todos requieren el encabezado X-API-KEY.
 */
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /** POST /api/empleados -> 201 Created */
    @PostMapping
    public ResponseEntity<EmpleadoResponse> crearEmpleado(@Valid @RequestBody EmpleadoRequest request) {
        EmpleadoResponse creado = empleadoService.crearEmpleado(request);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.id())
                .toUri();
        return ResponseEntity.created(ubicacion).body(creado);
    }

    /** GET /api/empleados y GET /api/empleados?especialidad=... -> 200 OK */
    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> listarEmpleados(
            @RequestParam(name = "especialidad", required = false) String especialidad) {
        return ResponseEntity.ok(empleadoService.listarEmpleados(especialidad));
    }

    /** GET /api/empleados/{id} -> 200 OK o 404 Not Found */
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> obtenerEmpleadoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadoPorId(id));
    }
}
