package Fundamentos_Java_Excepciones;

import java.util.Scanner;

class TextoVacioException extends Exception {
    public TextoVacioException(String mensaje) {
        super(mensaje);
    }
}

public class Ejercicio16_Validar_Texto_NoVacio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = "";
        boolean validar = false;

        while (!validar) {
            System.out.print("Ingrese un texto: ");
            texto = sc.nextLine().trim();

            try {
                validarNoVacio(texto);
                validar = true;
            } catch (TextoVacioException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Texto válido: " + texto);
        sc.close();
    }

    public static void validarNoVacio(String texto) throws TextoVacioException {
        if (texto.isEmpty()) {
            throw new TextoVacioException("El texto no puede estar vacío.");
        }
    }
}
