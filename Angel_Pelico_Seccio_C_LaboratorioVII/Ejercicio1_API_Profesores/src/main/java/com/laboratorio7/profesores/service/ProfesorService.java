package com.laboratorio7.profesores.service;

import com.laboratorio7.profesores.dto.ProfesorRequest;
import com.laboratorio7.profesores.dto.ProfesorResponse;

import java.util.List;

/**
 * Operaciones de negocio disponibles para los profesores.
 */
public interface ProfesorService {

    ProfesorResponse crearProfesor(ProfesorRequest request);

    /**
     * Lista los profesores. Si se indica una especialidad, filtra por ella
     * (sin distinguir mayúsculas de minúsculas).
     */
    List<ProfesorResponse> listarProfesores(String especialidad);

    ProfesorResponse obtenerProfesorPorId(Long id);
}
