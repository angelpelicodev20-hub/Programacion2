package com.laboratorio7.profesores.service.impl;

import com.laboratorio7.profesores.dto.ProfesorRequest;
import com.laboratorio7.profesores.dto.ProfesorResponse;
import com.laboratorio7.profesores.exception.CorreoDuplicadoException;
import com.laboratorio7.profesores.exception.ProfesorNotFoundException;
import com.laboratorio7.profesores.mapper.ProfesorMapper;
import com.laboratorio7.profesores.model.Profesor;
import com.laboratorio7.profesores.repository.ProfesorRepository;
import com.laboratorio7.profesores.service.ProfesorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private static final Logger log = LoggerFactory.getLogger(ProfesorServiceImpl.class);

    private final ProfesorRepository profesorRepository;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    public ProfesorResponse crearProfesor(ProfesorRequest request) {
        Profesor profesor = ProfesorMapper.toEntity(request);

        if (profesorRepository.existePorCorreo(profesor.getCorreo())) {
            throw new CorreoDuplicadoException(profesor.getCorreo());
        }

        Profesor guardado = profesorRepository.guardar(profesor);
        log.info("Profesor creado con id {}", guardado.getId());
        return ProfesorMapper.toResponse(guardado);
    }

    @Override
    public List<ProfesorResponse> listarProfesores(String especialidad) {
        List<Profesor> profesores = (especialidad == null || especialidad.isBlank())
                ? profesorRepository.buscarTodos()
                : profesorRepository.buscarPorEspecialidad(especialidad.trim());

        return profesores.stream()
                .map(ProfesorMapper::toResponse)
                .toList();
    }

    @Override
    public ProfesorResponse obtenerProfesorPorId(Long id) {
        return profesorRepository.buscarPorId(id)
                .map(ProfesorMapper::toResponse)
                .orElseThrow(() -> new ProfesorNotFoundException(id));
    }
}
