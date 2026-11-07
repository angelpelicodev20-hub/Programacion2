package com.laboratorio7.proveedores.service.impl;

import com.laboratorio7.proveedores.dto.ProveedorRequest;
import com.laboratorio7.proveedores.dto.ProveedorResponse;
import com.laboratorio7.proveedores.exception.CorreoDuplicadoException;
import com.laboratorio7.proveedores.exception.ProveedorNotFoundException;
import com.laboratorio7.proveedores.model.Estado;
import com.laboratorio7.proveedores.model.Proveedor;
import com.laboratorio7.proveedores.repository.ProveedorRepository;
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
class ProveedorServiceImplTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorService;

    @Test
    @DisplayName("Crear proveedor: guarda el proveedor y lo retorna con id y estado ACTIVO por defecto")
    void crearProveedor_conDatosValidos_retornaProveedorCreado() {
        ProveedorRequest request = new ProveedorRequest(
                "Ana", "López", "Ana.Lopez@Empresa.com", "Tecnología", null);

        when(proveedorRepository.existePorCorreo("ana.lopez@empresa.com")).thenReturn(false);
        when(proveedorRepository.guardar(any(Proveedor.class))).thenAnswer(invocacion -> {
            Proveedor proveedor = invocacion.getArgument(0);
            proveedor.setId(1L);
            return proveedor;
        });

        ProveedorResponse resultado = proveedorService.crearProveedor(request);

        assertEquals(1L, resultado.id());
        assertEquals("Ana", resultado.nombre());
        assertEquals("López", resultado.apellido());
        assertEquals("ana.lopez@empresa.com", resultado.correo());
        assertEquals("Tecnología", resultado.categoria());
        assertEquals(Estado.ACTIVO, resultado.estado());
        verify(proveedorRepository).guardar(any(Proveedor.class));
    }

    @Test
    @DisplayName("Obtener proveedor existente: retorna los datos del proveedor")
    void obtenerProveedorPorId_existente_retornaProveedor() {
        Proveedor proveedor = new Proveedor(1L, "Carlos", "Pérez", "carlos.perez@empresa.com", "Alimentos", Estado.ACTIVO);
        when(proveedorRepository.buscarPorId(1L)).thenReturn(Optional.of(proveedor));

        ProveedorResponse resultado = proveedorService.obtenerProveedorPorId(1L);

        assertEquals(1L, resultado.id());
        assertEquals("Carlos", resultado.nombre());
        assertEquals("Alimentos", resultado.categoria());
        verify(proveedorRepository).buscarPorId(1L);
    }

    @Test
    @DisplayName("Obtener proveedor inexistente: lanza ProveedorNotFoundException")
    void obtenerProveedorPorId_inexistente_lanzaExcepcion() {
        when(proveedorRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        ProveedorNotFoundException excepcion = assertThrows(
                ProveedorNotFoundException.class,
                () -> proveedorService.obtenerProveedorPorId(99L));

        assertEquals("No se encontró el proveedor con id 99", excepcion.getMessage());
    }

    @Test
    @DisplayName("Crear proveedor con correo duplicado: lanza CorreoDuplicadoException y no guarda")
    void crearProveedor_conCorreoDuplicado_lanzaExcepcion() {
        ProveedorRequest request = new ProveedorRequest(
                "Ana", "López", "ana.lopez@empresa.com", "Tecnología", Estado.ACTIVO);
        when(proveedorRepository.existePorCorreo("ana.lopez@empresa.com")).thenReturn(true);

        assertThrows(CorreoDuplicadoException.class, () -> proveedorService.crearProveedor(request));
        verify(proveedorRepository, never()).guardar(any(Proveedor.class));
    }

    @Test
    @DisplayName("Listar proveedores por categoría: delega el filtro al repositorio")
    void listarProveedores_conCategoria_filtraResultados() {
        Proveedor proveedor = new Proveedor(2L, "Lucía", "Gómez", "lucia.gomez@empresa.com", "Papelería", Estado.ACTIVO);
        when(proveedorRepository.buscarPorCategoria("Papelería")).thenReturn(List.of(proveedor));

        List<ProveedorResponse> resultado = proveedorService.listarProveedores("Papelería");

        assertEquals(1, resultado.size());
        assertEquals("Lucía", resultado.get(0).nombre());
        verify(proveedorRepository, never()).buscarTodos();
    }
}
