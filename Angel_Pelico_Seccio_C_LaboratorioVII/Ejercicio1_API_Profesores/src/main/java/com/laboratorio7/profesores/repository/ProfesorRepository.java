package com.laboratorio7.profesores.repository;

import com.laboratorio7.profesores.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria: los profesores se guardan en una lista
 * y se pierden al detener la aplicación.
 */
@Repository
public class ProfesorRepository {

    private final List<Profesor> profesores = new CopyOnWriteArrayList<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    public Profesor guardar(Profesor profesor) {
        profesor.setId(secuenciaId.incrementAndGet());
        profesores.add(profesor);
        return profesor;
    }

    public List<Profesor> buscarTodos() {
        return List.copyOf(profesores);
    }

    public Optional<Profesor> buscarPorId(Long id) {
        return profesores.stream()
                .filter(profesor -> profesor.getId().equals(id))
                .findFirst();
    }

    public List<Profesor> buscarPorEspecialidad(String especialidad) {
        return profesores.stream()
                .filter(profesor -> profesor.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    public boolean existePorCorreo(String correo) {
        return profesores.stream()
                .anyMatch(profesor -> profesor.getCorreo().equalsIgnoreCase(correo));
    }
}
