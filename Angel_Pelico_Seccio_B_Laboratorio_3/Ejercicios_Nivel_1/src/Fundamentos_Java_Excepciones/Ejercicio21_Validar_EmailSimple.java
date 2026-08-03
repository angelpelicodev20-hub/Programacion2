package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio21_Validar_EmailSimple {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un email: ");
        String email = sc.nextLine();
        boolean esValido = validarEmail(email);

        if(!esValido){
            System.out.println("El email ingresado no es válido: " + email);
        }
        sc.close();
    }

    public static boolean validarEmail(String email){
        try{
            if(!email.contains("@") || !email.contains(".")){
                throw new IllegalArgumentException("El email debe contener '@' y '.'");
            }else{
                System.out.println("El email ingresado es válido: " + email);
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
