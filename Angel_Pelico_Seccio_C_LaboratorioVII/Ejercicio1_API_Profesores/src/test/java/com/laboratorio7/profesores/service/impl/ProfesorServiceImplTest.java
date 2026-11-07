package com.laboratorio7.profesores.service.impl;

import com.laboratorio7.profesores.dto.ProfesorRequest;
import com.laboratorio7.profesores.dto.ProfesorResponse;
import com.laboratorio7.profesores.exception.CorreoDuplicadoException;
import com.laboratorio7.profesores.exception.ProfesorNotFoundException;
import com.laboratorio7.profesores.model.Estado;
import com.laboratorio7.profesores.model.Profesor;
import com.laboratorio7.profesores.repository.ProfesorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de la capa de servicio. El repositorio se simula con Mockito.
 */
@ExtendWith(MockitoExtension.class)
class ProfesorServiceImplTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorServiceImpl profesorService;

    @Test
    @DisplayName("Crear profesor: guarda el profesor y lo retorna con id y estado ACTIVO por defecto")
    void crearProfesor_conDatosValidos_retornaProfesorCreado() {
        ProfesorRequest request = new ProfesorRequest(
                "Ana", "López", "Ana.Lopez@Universidad.edu", "Matemáticas", null);

        when(profesorRepository.existePorCorreo("ana.lopez@universidad.edu")).thenReturn(false);
        when(profesorRepository.guardar(any(Profesor.class))).thenAnswer(invocacion -> {
            Profesor profesor = invocacion.getArgument(0);
            profesor.setId(1L);
            return profesor;
        });

        ProfesorResponse resultado = profesorService.crearProfesor(request);

        assertEquals(1L, resultado.id());
        assertEquals("Ana", resultado.nombre());
        assertEquals("López", resultado.apellido());
        assertEquals("ana.lopez@universidad.edu", resultado.correo());
        assertEquals("Matemáticas", resultado.especialidad());
        assertEquals(Estado.ACTIVO, resultado.estado());
        verify(profesorRepository).guardar(any(Profesor.class));
    }

    @Test
    @DisplayName("Obtener profesor existente: retorna los datos del profesor")
    void obtenerProfesorPorId_existente_retornaProfesor() {
        Profesor profesor = new Profesor(1L, "Carlos", "Pérez", "carlos.perez@universidad.edu", "Física", Estado.ACTIVO);
        when(profesorRepository.buscarPorId(1L)).thenReturn(Optional.of(profesor));

        ProfesorResponse resultado = profesorService.obtenerProfesorPorId(1L);

        assertEquals(1L, resultado.id());
        assertEquals("Carlos", resultado.nombre());
        assertEquals("Física", resultado.especialidad());
        verify(profesorRepository).buscarPorId(1L);
    }

    @Test
    @DisplayName("Obtener profesor inexistente: lanza ProfesorNotFoundException")
    void obtenerProfesorPorId_inexistente_lanzaExcepcion() {
        when(profesorRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        ProfesorNotFoundException excepcion = assertThrows(
                ProfesorNotFoundException.class,
                () -> profesorService.obtenerProfesorPorId(99L));

        assertEquals("No se encontró el profesor con id 99", excepcion.getMessage());
    }

    @Test
    @DisplayName("Crear profesor con correo duplicado: lanza CorreoDuplicadoException y no guarda")
    void crearProfesor_conCorreoDuplicado_lanzaExcepcion() {
        ProfesorRequest request = new ProfesorRequest(
                "Ana", "López", "ana.lopez@universidad.edu", "Matemáticas", Estado.ACTIVO);
        when(profesorRepository.existePorCorreo("ana.lopez@universidad.edu")).thenReturn(true);

        assertThrows(CorreoDuplicadoException.class, () -> profesorService.crearProfesor(request));
        verify(profesorRepository, never()).guardar(any(Profesor.class));
    }

    @Test
    @DisplayName("Listar profesores por especialidad: delega el filtro al repositorio")
    void listarProfesores_conEspecialidad_filtraResultados() {
        Profesor profesor = new Profesor(2L, "Lucía", "Gómez", "lucia.gomez@universidad.edu", "Química", Estado.ACTIVO);
        when(profesorRepository.buscarPorEspecialidad("Química")).thenReturn(List.of(profesor));

        List<ProfesorResponse> resultado = profesorService.listarProfesores("Química");

        assertEquals(1, resultado.size());
        assertEquals("Lucía", resultado.get(0).nombre());
        verify(profesorRepository, never()).buscarTodos();
    }
}
