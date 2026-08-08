import java.util.Scanner;

public class Ejercicio8_PromedioNotas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] notas = new double[5];
        double suma = 0;

        for (int i = 0; i < notas.length; i++) {
            System.out.print("Ingrese la nota " + (i + 1) + ": ");
            notas[i] = Double.parseDouble(sc.nextLine());
            suma += notas[i];
        }

        double promedio = suma / notas.length;
        System.out.println("El promedio de las notas es: " + promedio);

        sc.close();
    }
}
