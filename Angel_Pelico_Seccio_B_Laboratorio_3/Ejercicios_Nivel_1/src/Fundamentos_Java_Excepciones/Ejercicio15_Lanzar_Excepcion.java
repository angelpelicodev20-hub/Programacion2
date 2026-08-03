package Fundamentos_Java_Excepciones;

public class Ejercicio15_Lanzar_Excepcion {
    public static void main(String[] args) {
        try {
            verificarStock(0);
        } catch (StockInsuficienteException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }

    public static void verificarStock(int stock) throws StockInsuficienteException {
        if (stock <= 0) {
            throw new StockInsuficienteException("No hay stock disponible.");
        }
        System.out.println("Stock disponible: " + stock);
    }
}

// Excepción personalizada para indicar falta de stock
class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
