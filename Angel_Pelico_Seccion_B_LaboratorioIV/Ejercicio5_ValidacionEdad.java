import java.util.Scanner;

public class Ejercicio5_ValidacionEdad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Ingrese su edad: ");
            int edad = Integer.parseInt(sc.nextLine());

            if (edad < 0) {
                throw new IllegalArgumentException("La edad no puede ser negativa.");
            }

            System.out.println("Edad ingresada correctamente: " + edad + " años.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
