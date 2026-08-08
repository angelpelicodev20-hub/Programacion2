import java.util.Scanner;

public class Ejericicio11_ClasificacionEdad {
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        String clasificacion;

        if (edad < 0) {
            clasificacion = "Edad inválida";
        } else if (edad <= 12) {
            clasificacion = "Niño";
        } else if (edad <= 17) {
            clasificacion = "Adolescente";
        } else {
            clasificacion = "Adulto";
        }

        System.out.println("Clasificación: " + clasificacion);

        sc.close();
    }
}
