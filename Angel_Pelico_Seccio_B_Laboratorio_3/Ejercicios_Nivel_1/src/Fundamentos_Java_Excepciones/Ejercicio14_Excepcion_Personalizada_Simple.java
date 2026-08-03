package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio14_Excepcion_Personalizada_Simple {
    static class EdadNoValidaException extends Exception {
        public EdadNoValidaException(String mensaje) {
            super(mensaje);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Ingrese su edad: ");
            int edad = sc.nextInt();
            validarEdad(edad);
            System.out.println("Edad válida: " + edad);
        } catch (EdadNoValidaException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static void validarEdad(int edad) throws EdadNoValidaException {
        if (edad < 18) {
            throw new EdadNoValidaException("La edad debe ser mayor o igual a 18.");
        }
    }
}
