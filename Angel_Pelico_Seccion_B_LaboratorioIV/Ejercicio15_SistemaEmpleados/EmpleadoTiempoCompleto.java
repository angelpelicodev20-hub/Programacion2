package Ejercicio15_SistemaEmpleados;

class EmpleadoTiempoCompleto extends Empleado {
    private double salarioMensual;

    public EmpleadoTiempoCompleto(String nombre, String identificacion, double salarioMensual) {
        super(nombre, identificacion);
        if (salarioMensual < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo.");
        }
        this.salarioMensual = salarioMensual;
    }

    @Override
    public double calcularPago() {
        return salarioMensual;
    }
}
