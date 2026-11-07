package com.laboratorio7.empleados.service.impl;

import com.laboratorio7.empleados.dto.EmpleadoRequest;
import com.laboratorio7.empleados.dto.EmpleadoResponse;
import com.laboratorio7.empleados.exception.CorreoDuplicadoException;
import com.laboratorio7.empleados.exception.EmpleadoNotFoundException;
import com.laboratorio7.empleados.model.Estado;
import com.laboratorio7.empleados.model.Empleado;
import com.laboratorio7.empleados.repository.EmpleadoRepository;
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
class EmpleadoServiceImplTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    @Test
    @DisplayName("Crear empleado: guarda el empleado y lo retorna con id y estado ACTIVO por defecto")
    void crearEmpleado_conDatosValidos_retornaEmpleadoCreado() {
        EmpleadoRequest request = new EmpleadoRequest(
                "Ana", "López", "Ana.Lopez@Empresa.com", "Desarrollo de Software", null);

        when(empleadoRepository.existePorCorreo("ana.lopez@empresa.com")).thenReturn(false);
        when(empleadoRepository.guardar(any(Empleado.class))).thenAnswer(invocacion -> {
            Empleado empleado = invocacion.getArgument(0);
            empleado.setId(1L);
            return empleado;
        });

        EmpleadoResponse resultado = empleadoService.crearEmpleado(request);

        assertEquals(1L, resultado.id());
        assertEquals("Ana", resultado.nombre());
        assertEquals("López", resultado.apellido());
        assertEquals("ana.lopez@empresa.com", resultado.correo());
        assertEquals("Desarrollo de Software", resultado.especialidad());
        assertEquals(Estado.ACTIVO, resultado.estado());
        verify(empleadoRepository).guardar(any(Empleado.class));
    }

    @Test
    @DisplayName("Obtener empleado existente: retorna los datos del empleado")
    void obtenerEmpleadoPorId_existente_retornaEmpleado() {
        Empleado empleado = new Empleado(1L, "Carlos", "Pérez", "carlos.perez@empresa.com", "Contabilidad", Estado.ACTIVO);
        when(empleadoRepository.buscarPorId(1L)).thenReturn(Optional.of(empleado));

        EmpleadoResponse resultado = empleadoService.obtenerEmpleadoPorId(1L);

        assertEquals(1L, resultado.id());
        assertEquals("Carlos", resultado.nombre());
        assertEquals("Contabilidad", resultado.especialidad());
        verify(empleadoRepository).buscarPorId(1L);
    }

    @Test
    @DisplayName("Obtener empleado inexistente: lanza EmpleadoNotFoundException")
    void obtenerEmpleadoPorId_inexistente_lanzaExcepcion() {
        when(empleadoRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        EmpleadoNotFoundException excepcion = assertThrows(
                EmpleadoNotFoundException.class,
                () -> empleadoService.obtenerEmpleadoPorId(99L));

        assertEquals("No se encontró el empleado con id 99", excepcion.getMessage());
    }

    @Test
    @DisplayName("Crear empleado con correo duplicado: lanza CorreoDuplicadoException y no guarda")
    void crearEmpleado_conCorreoDuplicado_lanzaExcepcion() {
        EmpleadoRequest request = new EmpleadoRequest(
                "Ana", "López", "ana.lopez@empresa.com", "Desarrollo de Software", Estado.ACTIVO);
        when(empleadoRepository.existePorCorreo("ana.lopez@empresa.com")).thenReturn(true);

        assertThrows(CorreoDuplicadoException.class, () -> empleadoService.crearEmpleado(request));
        verify(empleadoRepository, never()).guardar(any(Empleado.class));
    }

    @Test
    @DisplayName("Listar empleados por especialidad: delega el filtro al repositorio")
    void listarEmpleados_conEspecialidad_filtraResultados() {
        Empleado empleado = new Empleado(2L, "Lucía", "Gómez", "lucia.gomez@empresa.com", "Recursos Humanos", Estado.ACTIVO);
        when(empleadoRepository.buscarPorEspecialidad("Recursos Humanos")).thenReturn(List.of(empleado));

        List<EmpleadoResponse> resultado = empleadoService.listarEmpleados("Recursos Humanos");

        assertEquals(1, resultado.size());
        assertEquals("Lucía", resultado.get(0).nombre());
        verify(empleadoRepository, never()).buscarTodos();
    }
}
