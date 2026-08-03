package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio11_Arreglo_Fuera_Rango {
 public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numeros = {10, 20, 30, 40, 50};

        System.out.println("El arreglo tiene " + numeros.length + " elementos (índices del 0 al " + (numeros.length - 1) + ").");

        int valor = leerElemento(sc, numeros);

        System.out.println("Valor obtenido: " + valor);
        sc.close();
    }

    public static int leerElemento(Scanner sc, int[] arreglo) {
        int valor = 0;
        boolean validar = false;

        while (!validar) {
            System.out.print("Ingrese un índice: ");

            try {
                int indice = Integer.parseInt(sc.nextLine().trim());
                valor = arreglo[indice];
                validar = true;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: índice fuera de rango. Debe estar entre 0 y " + (arreglo.length - 1) + ".");

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }

        return valor;
    }
}
