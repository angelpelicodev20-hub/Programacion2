package Fundamentos_Java_Excepciones;
import java.util.Scanner;
public class Ejercicio2_Validar_Entrada_Numerica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese un número entero: ");
            String linea = sc.nextLine();
            try {
                numero = Integer.parseInt(linea.trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número entero.");
            }
        }
        System.out.println("Número ingresado: " + numero);
        sc.close();
    }
}
