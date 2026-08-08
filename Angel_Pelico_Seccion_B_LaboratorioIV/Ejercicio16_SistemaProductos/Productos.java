package Ejercicio16_SistemaProductos;

public abstract class Productos {
        private String nombre;
    private double precio;

    public Productos(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void mostrarInformacion() {
        System.out.println("Producto: " + nombre);
        System.out.printf("Precio: Q%.2f%n", precio);
    }
}

class ProductoFisico extends Productos {
    private double peso;
    private double costoEnvio;

    public ProductoFisico(String nombre, double precio, double peso, double costoEnvio) {
        super(nombre, precio);
        this.peso = peso;
        this.costoEnvio = costoEnvio;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Producto Físico");
        System.out.println("Peso: " + peso + " kg");
        System.out.printf("Costo de envío: Q%.2f%n", costoEnvio);
        System.out.printf("Total a pagar: Q%.2f%n", getPrecio() + costoEnvio);
    }
}

class ProductoDigital extends Productos {
    private double tamanioMB;

    public ProductoDigital(String nombre, double precio, double tamanioMB) {
        super(nombre, precio);
        this.tamanioMB = tamanioMB;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Producto Digital");
        System.out.println("Tamaño: " + tamanioMB + " MB");
        System.out.println("Entrega: Descarga inmediata");
    }
}
