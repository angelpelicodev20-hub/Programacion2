package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio23_Validar_EntradaUsuario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Ingrese su nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese su edad: ");
            int edad = Integer.parseInt(sc.nextLine());

            System.out.print("Ingrese su sueldo: ");
            double sueldo = Double.parseDouble(sc.nextLine());

            System.out.println("Nombre: " + nombre + ", Edad: " + edad + ", Sueldo: " + sueldo);

        } catch (NumberFormatException e) {
            System.out.println("Error: ingresó un valor numérico inválido.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            System.out.println("Fin del proceso de entrada.");
            sc.close();
        }
    }
}
