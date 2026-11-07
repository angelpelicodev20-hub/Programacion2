package com.laboratorio7.empleados.service.impl;

import com.laboratorio7.empleados.dto.EmpleadoRequest;
import com.laboratorio7.empleados.dto.EmpleadoResponse;
import com.laboratorio7.empleados.exception.CorreoDuplicadoException;
import com.laboratorio7.empleados.exception.EmpleadoNotFoundException;
import com.laboratorio7.empleados.mapper.EmpleadoMapper;
import com.laboratorio7.empleados.model.Empleado;
import com.laboratorio7.empleados.repository.EmpleadoRepository;
import com.laboratorio7.empleados.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public EmpleadoResponse crearEmpleado(EmpleadoRequest request) {
        Empleado empleado = EmpleadoMapper.toEntity(request);

        if (empleadoRepository.existePorCorreo(empleado.getCorreo())) {
            throw new CorreoDuplicadoException(empleado.getCorreo());
        }

        Empleado guardado = empleadoRepository.guardar(empleado);
        log.info("Empleado creado con id {}", guardado.getId());
        return EmpleadoMapper.toResponse(guardado);
    }

    @Override
    public List<EmpleadoResponse> listarEmpleados(String especialidad) {
        List<Empleado> empleados = (especialidad == null || especialidad.isBlank())
                ? empleadoRepository.buscarTodos()
                : empleadoRepository.buscarPorEspecialidad(especialidad.trim());

        return empleados.stream()
                .map(EmpleadoMapper::toResponse)
                .toList();
    }

    @Override
    public EmpleadoResponse obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.buscarPorId(id)
                .map(EmpleadoMapper::toResponse)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }
}
