package Ejercicio15_SistemaEmpleados;

public abstract class Empleado {
    private String nombre;
    private String identificacion;

    public Empleado(String nombre, String identificacion) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }

    public abstract double calcularPago();

    public void mostrarDatos() {
        System.out.println("Empleado: " + nombre + " (ID: " + identificacion + ")");
        System.out.printf("Pago calculado: Q%.2f%n", calcularPago());
    }
}
