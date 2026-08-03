package Fundamentos_Java_Excepciones;

import java.util.Scanner;

class LongitudInvalidaException extends Exception {
    public LongitudInvalidaException(String mensaje) {
        super(mensaje);
    }
}

public class Ejercicio22_Longitud_Texto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = "";
        boolean validar = false;

        while (!validar) {
            System.out.print("Ingrese un texto (entre 5 y 15 caracteres): ");
            texto = sc.nextLine();

            try {
                validarLongitud(texto, 5, 15);
                validar = true;
            } catch (LongitudInvalidaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Texto válido: " + texto);
        sc.close();
    }

    public static void validarLongitud(String texto, int min, int max) throws LongitudInvalidaException {
        if (texto.length() < min || texto.length() > max) {
            throw new LongitudInvalidaException(
                "El texto debe tener entre " + min + " y " + max + " caracteres (tiene " + texto.length() + ")."
            );
        }
    }
}