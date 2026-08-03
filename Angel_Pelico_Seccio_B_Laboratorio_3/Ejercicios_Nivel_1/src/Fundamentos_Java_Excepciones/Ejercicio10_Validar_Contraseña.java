package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio10_Validar_Contraseña {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String contraseña = ValidarCotrsañe(sc);

        System.out.println("Contraseña ingresada: " + contraseña);
        sc.close();
    }

    public static String ValidarCotrsañe(Scanner sc){
        String contraseña = "";
        boolean validar = false;

        while (!validar) {
            System.out.print("Ingrese una contraseña de al menos 8 caracteres: ");

            try {
                contraseña = sc.nextLine();

                if (contraseña.length() < 8) {
                    System.out.println("Error: La contraseña debe tener al menos 8 caracteres.");
                } else {
                    validar = true;
                }

            } catch (Exception e) {
                System.out.println("Error: debe ingresar una contraseña válida.");
                sc.next(); 
            }
        }

        return contraseña;
    } 
}
