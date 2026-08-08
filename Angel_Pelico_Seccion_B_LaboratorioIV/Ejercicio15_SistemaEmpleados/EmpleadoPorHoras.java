package Ejercicio15_SistemaEmpleados;

class EmpleadoPorHoras extends Empleado{
    private double horasTrabajadas;
    private double pagoPorHora;

    public EmpleadoPorHoras(String nombre, String identificacion, double horasTrabajadas, double pagoPorHora) {
        super(nombre, identificacion);
        if (horasTrabajadas < 0 || pagoPorHora < 0) {
            throw new IllegalArgumentException("Las horas y el pago por hora no pueden ser negativos.");
        }
        this.horasTrabajadas = horasTrabajadas;
        this.pagoPorHora = pagoPorHora;
    }

    @Override
    public double calcularPago() {
        return horasTrabajadas * pagoPorHora;
    }
}
