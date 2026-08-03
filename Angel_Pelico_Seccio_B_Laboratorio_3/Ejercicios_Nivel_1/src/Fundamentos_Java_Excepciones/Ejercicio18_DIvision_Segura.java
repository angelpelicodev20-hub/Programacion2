package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio18_DIvision_Segura {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el numerador: ");
        int numerador = sc.nextInt();
        System.out.print("Ingrese el denominador: ");
        int denominador = sc.nextInt();
        int resultado = DivisionSegura(numerador, denominador);
        if(denominador == 0){
            System.out.println("El denominador es cero.");
        }else{
            System.out.println("El resultado de la división es: " + resultado);
        }
        sc.close();
    }

    public static int DivisionSegura(int numeroador, int denominador){
        
        try {
            return numeroador / denominador;
        } catch (ArithmeticException e) {
            System.out.println("Error: División por cero no permitida.");
        }
        return 0;
    }
}

