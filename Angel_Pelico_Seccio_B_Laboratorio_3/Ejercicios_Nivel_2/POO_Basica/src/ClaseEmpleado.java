public class ClaseEmpleado {

    private String nombre;
    private double salario;

    // Constructor
    public ClaseEmpleado(String nombre, double salario) {
        this.nombre = nombre;
        setSalario(salario);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalario() {
        return salario;
    }

    //Encapsular salario / 8. Validar salario positivo
    public void setSalario(double salario) {
        if (salario <= 0) {
            System.out.println("Error: '" + salario + "' no es un salario válido. Debe ser mayor a cero.");
        } else {
            this.salario = salario;
        }
    }

    //Mostrar datos
    public void mostrarDatos() {
        System.out.println("Empleado: " + nombre + " | Salario: Q" + salario);
    }

    public static void main(String[] args) {
        // Crear varios objetos
        ClaseEmpleado empleado1 = new ClaseEmpleado("Carlos", 1200.0);
        empleado1.mostrarDatos();

        ClaseEmpleado empleado2 = new ClaseEmpleado("Ana", -500.0); // inválido
        empleado2.mostrarDatos();

        ClaseEmpleado empleado3 = new ClaseEmpleado("Luis", 2500.0);
        empleado3.mostrarDatos();
    }
}
