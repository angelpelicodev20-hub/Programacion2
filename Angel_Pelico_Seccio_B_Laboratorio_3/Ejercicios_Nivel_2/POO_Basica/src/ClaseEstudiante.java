import java.util.ArrayList;
import java.util.List;

public class ClaseEstudiante {
    private String nombre;
    private List<Double> notas;

    // Constructor
    public ClaseEstudiante(String nombre) {
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Double> getNotas() {
        return notas;
    }

    //Agregar notas privadas / 5. Validar notas entre 0 y 100
    public void agregarNota(double nota) {
        if (nota < 0 || nota > 100) {
            System.out.println("Error: '" + nota + "' no es una nota válida. Debe estar entre 0 y 100.");
        } else {
            notas.add(nota);
        }
    }

    //Calcular promedio
    public double calcularPromedio() {
        if (notas.isEmpty()) {
            System.out.println("El estudiante no tiene notas registradas.");
            return 0.0;
        }

        double suma = 0.0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    //Mostrar resultado
    public void mostrarResultado() {
        System.out.println("Estudiante: " + nombre);
        System.out.println("Notas: " + notas);
        System.out.println("Promedio: " + calcularPromedio());
    }

    public static void main(String[] args) {
        ClaseEstudiante estudiante1 = new ClaseEstudiante("Juan");
        estudiante1.agregarNota(85);
        estudiante1.agregarNota(90);
        estudiante1.agregarNota(150); // inválida
        estudiante1.agregarNota(70);
        estudiante1.mostrarResultado();
    }
}
