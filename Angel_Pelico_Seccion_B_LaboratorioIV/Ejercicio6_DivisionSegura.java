import java.util.Scanner;

public class Ejercicio6_DivisionSegura {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Ingrese el numerador: ");
            double numerador = Double.parseDouble(sc.nextLine());

            System.out.print("Ingrese el denominador: ");
            double denominador = Double.parseDouble(sc.nextLine());

            if (denominador == 0) {
                throw new ArithmeticException("No se puede dividir entre cero.");
            }

            double resultado = numerador / denominador;
            System.out.println("Resultado: " + resultado);

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar valores numéricos válidos.");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
