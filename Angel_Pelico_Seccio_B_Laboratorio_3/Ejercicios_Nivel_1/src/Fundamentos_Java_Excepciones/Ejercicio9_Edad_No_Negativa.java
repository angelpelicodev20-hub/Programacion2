package Fundamentos_Java_Excepciones;

import java.util.Scanner;

public class Ejercicio9_Edad_No_Negativa {
    public static void main(String [] args){
        Scanner sc = new Scanner (System.in);
        int Edad;
        boolean Edadvalidar = false;
        
        do{
            System.out.print("Ingrese su edad: ");
            Edad = sc.nextInt();
            
            try{
                if(Edad < 0){
                    System.out.println("Error: '" + Edad + "' la edad no puede ser negativa.");
                } else {
                    Edadvalidar = true;
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un Edad válido.");
                sc.nextLine(); 
            }
        } while (!Edadvalidar);
        System.out.println("Edad ingresada: " + Edad);
        sc.close();
    }
}
