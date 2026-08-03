import java.util.ArrayList;
import java.util.List;

public class ClaseProductos {
    private String nombre;
    private double precio;

    // Constructor
    public ClaseProductos(String nombre, double precio) {
        this.nombre = nombre;
        setPrecio(precio); // reutiliza el setter para validar desde el constructor
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    // 3. Validar precio positivo
    public void setPrecio(double precio) {
        if (precio <= 0) {
            System.out.println("Error: '" + precio + "' no es un precio válido. Debe ser mayor a cero.");
        } else {
            this.precio = precio;
        }
    }

    // 4. Método mostrar producto
    public void mostrarProducto() {
        System.out.println("Producto: " + nombre + " | Precio: $" + precio);
    }

    public static void main(String[] args) {
         List<ClaseProductos> productos = new ArrayList<>();

        productos.add(new ClaseProductos("Laptop", 899.99));
        productos.add(new ClaseProductos("Mouse", 15.50));
        productos.add(new ClaseProductos("Teclado", -10.0)); // precio inválido
        productos.add(new ClaseProductos("Monitor", 250.0));

        System.out.println("\n--- Lista de productos ---");
        for (ClaseProductos p : productos) {
            p.mostrarProducto();
        }
    }
}
