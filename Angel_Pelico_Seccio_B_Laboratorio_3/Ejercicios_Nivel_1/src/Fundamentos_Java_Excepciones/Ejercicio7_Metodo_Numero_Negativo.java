package Fundamentos_Java_Excepciones;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio7_Metodo_Numero_Negativo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numero = pedirNumeroPositivo(sc);

        System.out.println("Número ingresado: " + numero);
        sc.close();
    }

    public static int pedirNumeroPositivo(Scanner sc) {
        int numero = 0;
        boolean validar = false;

        while (!validar) {
            System.out.print("Ingrese un numero positivo: ");

            try {
                numero = sc.nextInt();

                if (numero < 0) {
                    System.out.println("Error: '" + numero + "' es un número negativo, no un número positivo.");
                } else {
                    validar = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
                sc.next(); 
            }
        }

        return numero;
    }
}