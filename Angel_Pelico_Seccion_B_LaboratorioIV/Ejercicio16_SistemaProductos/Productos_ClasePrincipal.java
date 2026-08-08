package Ejercicio16_SistemaProductos;

public class Productos_ClasePrincipal {
    public static void main(String[] args) {
        Productos[] productos = {
                new ProductoFisico("Laptop HP", 4500, 2.1, 75),
                new ProductoDigital("Curso de Java", 350, 850)
        };

        for (Productos p : productos) {
            p.mostrarInformacion();
            System.out.println();
        }
    }
}
