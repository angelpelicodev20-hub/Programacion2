package Fundamentos_Java_Excepciones;
import java.util.Scanner;
public class Ejercicio1_Capturar_Excepciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Ingrese el dividendo: ");
        int dividendo = sc.nextInt();
        System.out.print("Ingrese el divisor: ");
        int divisor = sc.nextInt();

        try {
            int resultado = dividendo / divisor;
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("No se puede dividir entre cero.");
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
