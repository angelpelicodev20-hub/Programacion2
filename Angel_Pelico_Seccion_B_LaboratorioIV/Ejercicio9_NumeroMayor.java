import java.util.Scanner;

public class Ejercicio9_NumeroMayor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el primer número: ");
        double n1 = Double.parseDouble(sc.nextLine());

        System.out.print("Ingrese el segundo número: ");
        double n2 = Double.parseDouble(sc.nextLine());

        System.out.print("Ingrese el tercer número: ");
        double n3 = Double.parseDouble(sc.nextLine());

        double mayor = n1;
        if (n2 > mayor) mayor = n2;
        if (n3 > mayor) mayor = n3;

        System.out.println("El número mayor es: " + mayor);

        sc.close();
    }
}
