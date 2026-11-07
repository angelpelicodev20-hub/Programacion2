package com.laboratorio7.proveedores.service.impl;

import com.laboratorio7.proveedores.dto.ProveedorRequest;
import com.laboratorio7.proveedores.dto.ProveedorResponse;
import com.laboratorio7.proveedores.exception.CorreoDuplicadoException;
import com.laboratorio7.proveedores.exception.ProveedorNotFoundException;
import com.laboratorio7.proveedores.mapper.ProveedorMapper;
import com.laboratorio7.proveedores.model.Proveedor;
import com.laboratorio7.proveedores.repository.ProveedorRepository;
import com.laboratorio7.proveedores.service.ProveedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private static final Logger log = LoggerFactory.getLogger(ProveedorServiceImpl.class);

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public ProveedorResponse crearProveedor(ProveedorRequest request) {
        Proveedor proveedor = ProveedorMapper.toEntity(request);

        if (proveedorRepository.existePorCorreo(proveedor.getCorreo())) {
            throw new CorreoDuplicadoException(proveedor.getCorreo());
        }

        Proveedor guardado = proveedorRepository.guardar(proveedor);
        log.info("Proveedor creado con id {}", guardado.getId());
        return ProveedorMapper.toResponse(guardado);
    }

    @Override
    public List<ProveedorResponse> listarProveedores(String categoria) {
        List<Proveedor> proveedores = (categoria == null || categoria.isBlank())
                ? proveedorRepository.buscarTodos()
                : proveedorRepository.buscarPorCategoria(categoria.trim());

        return proveedores.stream()
                .map(ProveedorMapper::toResponse)
                .toList();
    }

    @Override
    public ProveedorResponse obtenerProveedorPorId(Long id) {
        return proveedorRepository.buscarPorId(id)
                .map(ProveedorMapper::toResponse)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
    }
}
