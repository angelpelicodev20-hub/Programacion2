package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio24_Reintentar_Lectura {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero = leerConReintentos(sc, 3);

        if (numero != Integer.MIN_VALUE) {
            System.out.println("Número ingresado correctamente: " + numero);
        } else {
            System.out.println("Se agotaron los intentos. No se ingresó un número válido.");
        }

        sc.close();
    }

    public static int leerConReintentos(Scanner sc, int maxIntentos) {
        int intentos = 0;

        while (intentos < maxIntentos) {
            System.out.print("Ingrese un número (intento " + (intentos + 1) + " de " + maxIntentos + "): ");

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                intentos++;
                System.out.println("Error: entrada inválida. Le quedan " + (maxIntentos - intentos) + " intento(s).");
            }
        }

        return Integer.MIN_VALUE;
    }
}
