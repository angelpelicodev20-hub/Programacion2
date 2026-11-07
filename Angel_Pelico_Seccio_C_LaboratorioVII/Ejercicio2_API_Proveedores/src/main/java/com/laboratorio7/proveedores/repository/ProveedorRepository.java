package com.laboratorio7.proveedores.repository;

import com.laboratorio7.proveedores.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria: los proveedores se guardan en una lista
 * y se pierden al detener la aplicación.
 */
@Repository
public class ProveedorRepository {

    private final List<Proveedor> proveedores = new CopyOnWriteArrayList<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    public Proveedor guardar(Proveedor proveedor) {
        proveedor.setId(secuenciaId.incrementAndGet());
        proveedores.add(proveedor);
        return proveedor;
    }

    public List<Proveedor> buscarTodos() {
        return List.copyOf(proveedores);
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.getId().equals(id))
                .findFirst();
    }

    public List<Proveedor> buscarPorCategoria(String categoria) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public boolean existePorCorreo(String correo) {
        return proveedores.stream()
                .anyMatch(proveedor -> proveedor.getCorreo().equalsIgnoreCase(correo));
    }
}
