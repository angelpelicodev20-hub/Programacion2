package com.laboratorio7.proveedores.controller;

import com.laboratorio7.proveedores.dto.ProveedorRequest;
import com.laboratorio7.proveedores.dto.ProveedorResponse;
import com.laboratorio7.proveedores.service.ProveedorService;
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
 * Endpoints REST para administrar proveedores.
 * Todos requieren el encabezado X-API-KEY.
 */
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    /** POST /api/proveedores -> 201 Created */
    @PostMapping
    public ResponseEntity<ProveedorResponse> crearProveedor(@Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse creado = proveedorService.crearProveedor(request);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.id())
                .toUri();
        return ResponseEntity.created(ubicacion).body(creado);
    }

    /** GET /api/proveedores y GET /api/proveedores?categoria=... -> 200 OK */
    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> listarProveedores(
            @RequestParam(name = "categoria", required = false) String categoria) {
        return ResponseEntity.ok(proveedorService.listarProveedores(categoria));
    }

    /** GET /api/proveedores/{id} -> 200 OK o 404 Not Found */
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> obtenerProveedorPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(proveedorService.obtenerProveedorPorId(id));
    }
}
