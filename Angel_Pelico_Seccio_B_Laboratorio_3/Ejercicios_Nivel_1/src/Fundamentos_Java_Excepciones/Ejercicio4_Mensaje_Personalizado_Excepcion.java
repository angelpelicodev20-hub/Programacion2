package Fundamentos_Java_Excepciones;
import java.util.Scanner;
public class Ejercicio4_Mensaje_Personalizado_Excepcion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String palabra;
        boolean validar = false;

        do {
            System.out.print("Ingrese una palabra: ");
            palabra = sc.nextLine();

            try{
                Double.parseDouble(palabra);
                System.out.println("Error: '" + palabra + "' es un número, no una palabra.");
            } catch (NumberFormatException e) {
                validar = true;
            }
        } while (!validar);
        System.out.println("Palabra ingresada: " + palabra);
        sc.close();
    }
}
