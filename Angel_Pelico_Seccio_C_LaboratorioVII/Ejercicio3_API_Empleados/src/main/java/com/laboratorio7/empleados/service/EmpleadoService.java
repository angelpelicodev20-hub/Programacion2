package com.laboratorio7.empleados.service;

import com.laboratorio7.empleados.dto.EmpleadoRequest;
import com.laboratorio7.empleados.dto.EmpleadoResponse;

import java.util.List;

/**
 * Operaciones de negocio disponibles para los empleados.
 */
public interface EmpleadoService {

    EmpleadoResponse crearEmpleado(EmpleadoRequest request);

    /**
     * Lista los empleados. Si se indica una especialidad, filtra por ella
     * (sin distinguir mayúsculas de minúsculas).
     */
    List<EmpleadoResponse> listarEmpleados(String especialidad);

    EmpleadoResponse obtenerEmpleadoPorId(Long id);
}
