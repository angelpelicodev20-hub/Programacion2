package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio17_Manejar_Error_charAt {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese una palabra: ");
        String palabra = sc.nextLine();

        System.out.print("Ingrese una posición: ");
        int posicion = Integer.parseInt(sc.nextLine());

        try {
            char letra = palabra.charAt(posicion);
            System.out.println("La letra en la posición " + posicion + " es: " + letra);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: posición fuera de rango. La palabra tiene " + palabra.length() + " caracteres (0 a " + (palabra.length() - 1) + ").");
        }

        sc.close();
    }
}
