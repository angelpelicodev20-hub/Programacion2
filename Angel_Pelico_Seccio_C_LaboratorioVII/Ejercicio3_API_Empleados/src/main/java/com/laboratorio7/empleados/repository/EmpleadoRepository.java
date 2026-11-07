package com.laboratorio7.empleados.repository;

import com.laboratorio7.empleados.model.Empleado;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria: los empleados se guardan en una lista
 * y se pierden al detener la aplicación.
 */
@Repository
public class EmpleadoRepository {

    private final List<Empleado> empleados = new CopyOnWriteArrayList<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    public Empleado guardar(Empleado empleado) {
        empleado.setId(secuenciaId.incrementAndGet());
        empleados.add(empleado);
        return empleado;
    }

    public List<Empleado> buscarTodos() {
        return List.copyOf(empleados);
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleados.stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst();
    }

    public List<Empleado> buscarPorEspecialidad(String especialidad) {
        return empleados.stream()
                .filter(empleado -> empleado.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    public boolean existePorCorreo(String correo) {
        return empleados.stream()
                .anyMatch(empleado -> empleado.getCorreo().equalsIgnoreCase(correo));
    }
}
