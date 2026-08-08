import java.util.Scanner;

public class Ejercicio10_EstadisticasBasicas {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] numeros = new double[5];
        double suma = 0;
        double mayor = Double.NEGATIVE_INFINITY;
        double menor = Double.POSITIVE_INFINITY;

        for (int i = 0; i < numeros.length; i++) {
            System.out.print("Ingrese el número " + (i + 1) + ": ");
            numeros[i] = Double.parseDouble(sc.nextLine());
            suma += numeros[i];

            if (numeros[i] > mayor) mayor = numeros[i];
            if (numeros[i] < menor) menor = numeros[i];
        }

        double promedio = suma / numeros.length;

        System.out.println("\n--- Estadísticas ---");
        System.out.println("Suma: " + suma);
        System.out.println("Promedio: " + promedio);
        System.out.println("Mayor: " + mayor);
        System.out.println("Menor: " + menor);

        sc.close();
    }
}
