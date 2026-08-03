package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio13_Usar_throw {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese su edad: ");
        int edad = sc.nextInt();
        try {
            if (edad < 18) {
                throw new IllegalArgumentException("Error: La edad debe ser mayor o igual a 18.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }
}
