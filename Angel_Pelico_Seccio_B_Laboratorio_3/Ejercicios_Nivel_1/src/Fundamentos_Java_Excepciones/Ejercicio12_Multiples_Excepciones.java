package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio12_Multiples_Excepciones {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arreglo = {50,75,100,125,150};

        try{
            System.out.println("Ingrese un índice del arreglo: ");
            int indice = sc.nextInt();

            if (indice >= arreglo.length || indice < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            System.out.println("Ingrese un número para dividir el valor del arreglo: ");
            int divisor = sc.nextInt();

            System.out.println("Valor en el índice " + indice + ": " + arreglo[indice]);

            int resultado = arreglo[indice] / divisor;
            System.out.println("El resultado es: " + resultado);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera de los límites del arreglo.");
        } catch (ArithmeticException e) {
            System.out.println("Error: División por cero no permitida.");
        } catch (Exception e) {
            System.out.println("Error: Ocurrió una excepción inesperada.");
        } 
        sc.close();
    }
}
