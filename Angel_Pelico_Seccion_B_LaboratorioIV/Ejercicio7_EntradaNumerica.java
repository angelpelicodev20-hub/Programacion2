import java.util.Scanner;

public class Ejercicio7_EntradaNumerica {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Ingrese un número entero: ");
            int numero = Integer.parseInt(sc.nextLine());
            System.out.println("Número ingresado correctamente: " + numero);

        } catch (NumberFormatException e) {
            System.out.println("Error: El valor ingresado no es un número válido (ingresó texto).");
        } finally {
            sc.close();
        }
    }
}
