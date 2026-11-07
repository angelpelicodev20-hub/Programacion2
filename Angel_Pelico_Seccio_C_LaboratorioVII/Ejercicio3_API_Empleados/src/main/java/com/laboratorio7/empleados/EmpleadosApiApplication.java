package com.laboratorio7.empleados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la API para administrar empleados.
 */
@SpringBootApplication
public class EmpleadosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpleadosApiApplication.class, args);
    }
}
