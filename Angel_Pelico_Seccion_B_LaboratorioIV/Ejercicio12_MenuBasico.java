import java.util.Scanner;

public class Ejercicio12_MenuBasico {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- MENÚ ---");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.print("Seleccione una opción: ");
        int opcion = Integer.parseInt(sc.nextLine());

        System.out.print("Ingrese el primer número: ");
        double n1 = Double.parseDouble(sc.nextLine());

        System.out.print("Ingrese el segundo número: ");
        double n2 = Double.parseDouble(sc.nextLine());

        switch (opcion) {
            case 1:
                System.out.println("Resultado: " + (n1 + n2));
                break;
            case 2:
                System.out.println("Resultado: " + (n1 - n2));
                break;
            default:
                System.out.println("Opción no válida.");
        }

        sc.close();
    }
}
