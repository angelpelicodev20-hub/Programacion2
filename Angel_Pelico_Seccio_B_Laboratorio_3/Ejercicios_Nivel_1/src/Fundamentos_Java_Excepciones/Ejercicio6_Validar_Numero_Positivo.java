package Fundamentos_Java_Excepciones;
import java.util.Scanner;
public class Ejercicio6_Validar_Numero_Positivo {
    public static void main(String [] args){
        Scanner sc = new Scanner (System.in);
        int numero;
        boolean validar = false;
        
        do{
            System.out.print("Ingrese un numero positivo: ");
            numero = sc.nextInt();
            
            try{
                if(numero < 0){
                    System.out.println("Error: '" + numero + "' es un número negativo, no un número positivo.");
                } else {
                    validar = true;
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                sc.nextLine(); 
            }
        } while (!validar);
        System.out.println("Número ingresado: " + numero);
        sc.close();
    }
}
