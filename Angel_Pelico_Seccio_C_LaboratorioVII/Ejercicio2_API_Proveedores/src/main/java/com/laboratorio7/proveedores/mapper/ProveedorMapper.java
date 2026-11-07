package com.laboratorio7.proveedores.mapper;

import com.laboratorio7.proveedores.dto.ProveedorRequest;
import com.laboratorio7.proveedores.dto.ProveedorResponse;
import com.laboratorio7.proveedores.model.Estado;
import com.laboratorio7.proveedores.model.Proveedor;

import java.util.Locale;

/**
 * Convierte entre la entidad {@link Proveedor} y sus DTOs.
 */
public final class ProveedorMapper {

    private ProveedorMapper() {
    }

    public static Proveedor toEntity(ProveedorRequest request) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(request.nombre().trim());
        proveedor.setApellido(request.apellido().trim());
        proveedor.setCorreo(request.correo().trim().toLowerCase(Locale.ROOT));
        proveedor.setCategoria(request.categoria().trim());
        proveedor.setEstado(request.estado() != null ? request.estado() : Estado.ACTIVO);
        return proveedor;
    }

    public static ProveedorResponse toResponse(Proveedor proveedor) {
        return new ProveedorResponse(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getApellido(),
                proveedor.getCorreo(),
                proveedor.getCategoria(),
                proveedor.getEstado()
        );
    }
}
