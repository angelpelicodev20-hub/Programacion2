package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio5_Finally {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingresa un número: ");
            String entrada = scanner.nextLine();
            int numero = Integer.parseInt(entrada);
            System.out.println("Correcto: " + numero);

        } catch (NumberFormatException e) {
            System.out.println("Error: entrada inválida.");

        } finally {
            System.out.println("Cerrando el Scanner...");
            scanner.close(); // se cierra siempre, haya error o no
        }
    }
}
