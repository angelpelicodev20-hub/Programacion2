package Ejercicio15_SistemaEmpleados;

public class Empleados_ClasePrincipal {
    public static void main(String[] args) {
        Empleado[] empleados = {
                new EmpleadoTiempoCompleto("Ana López", "E001", 8000),
                new EmpleadoPorHoras("Carlos Pérez", "E002", 120, 45.5)
        };

        for (Empleado e : empleados) {
            e.mostrarDatos();
            System.out.println();
        }
    }
}
