package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio19_Validar_NumeroMayor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un número mayor que 100: ");
        int numero = sc.nextInt();
        int salida = validarNumeroMayor(numero);
        if(salida < 100){
            System.out.println("El número ingresado no es válido: " + numero);
        }
        sc.close();
    }
    
    public static int validarNumeroMayor(int numero){
        try{
            if(numero < 100){
                throw new IllegalArgumentException("El número debe ser mayor que 100");
            }else{
                System.out.println("El número ingresado es válido: " + numero);
            }
            return numero;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
}
