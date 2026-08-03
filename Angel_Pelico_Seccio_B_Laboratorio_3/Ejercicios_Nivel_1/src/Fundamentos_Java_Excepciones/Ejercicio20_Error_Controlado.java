package Fundamentos_Java_Excepciones;

public class Ejercicio20_Error_Controlado {
    public static void main(String[] args) {
        try {
            procesarPedido(-3);
        } catch (IllegalStateException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }
        System.out.println("El programa continúa ejecutándose normalmente.");
    }

    public static void procesarPedido(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalStateException("No se puede procesar un pedido con cantidad " + cantidad + ".");
        }
        System.out.println("Procesando pedido de " + cantidad + " unidades...");
    }
}
