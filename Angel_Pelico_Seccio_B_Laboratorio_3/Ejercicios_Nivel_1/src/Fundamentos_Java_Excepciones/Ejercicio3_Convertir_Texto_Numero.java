package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio3_Convertir_Texto_Numero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        boolean salidaNumero = false;

        while (!salidaNumero) {
            System.out.print("Ingrese un texto para convertir a número: ");
            String texto = sc.nextLine();

            try {
                numero = Integer.parseInt(texto.trim());
                salidaNumero = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: '" + texto + "' no es un número válido.");
            } catch (NullPointerException e) {
                System.out.println("Error: El texto ingresado es nulo.");
            }
        }
        System.out.println("El texto convertido a número es: " + numero);
        sc.close();
    }
}
