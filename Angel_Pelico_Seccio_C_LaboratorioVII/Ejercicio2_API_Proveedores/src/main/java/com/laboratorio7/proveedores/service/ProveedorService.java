package com.laboratorio7.proveedores.service;

import com.laboratorio7.proveedores.dto.ProveedorRequest;
import com.laboratorio7.proveedores.dto.ProveedorResponse;

import java.util.List;

/**
 * Operaciones de negocio disponibles para los proveedores.
 */
public interface ProveedorService {

    ProveedorResponse crearProveedor(ProveedorRequest request);

    /**
     * Lista los proveedores. Si se indica una categoría, filtra por ella
     * (sin distinguir mayúsculas de minúsculas).
     */
    List<ProveedorResponse> listarProveedores(String categoria);

    ProveedorResponse obtenerProveedorPorId(Long id);
}
