package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio25_Menu_Excepciones {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();

            try {
                int opcion = Integer.parseInt(sc.nextLine().trim());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese dividendo: ");
                        int a = Integer.parseInt(sc.nextLine());
                        System.out.print("Ingrese divisor: ");
                        int b = Integer.parseInt(sc.nextLine());
                        System.out.println("Resultado: " + (a / b));
                        break;

                    case 2:
                        System.out.print("Ingrese una palabra: ");
                        String palabra = sc.nextLine();
                        System.out.print("Ingrese posición: ");
                        int pos = Integer.parseInt(sc.nextLine());
                        System.out.println("Carácter: " + palabra.charAt(pos));
                        break;

                    case 3:
                        salir = true;
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Error: opción no válida. Elija 1, 2 o 3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido.");
            } catch (ArithmeticException e) {
                System.out.println("Error: no se puede dividir entre cero.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Error: posición fuera de rango en la palabra.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }

            System.out.println();
        }

        sc.close();
    }

    public static void mostrarMenu() {
        System.out.println("===== MENÚ =====");
        System.out.println("1. Dividir dos números");
        System.out.println("2. Obtener carácter de una palabra");
        System.out.println("3. Salir");
        System.out.print("Elija una opción: ");
    }
}
