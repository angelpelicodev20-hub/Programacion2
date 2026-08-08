package Ejercicio2_SistemaVehiculos;

public class Vehiculos_ClasePrincipal {
    public static void main(String[] args) {
        Carro carro = new Carro("Honda", "2026", 1500);
        Moto moto = new Moto("Yamaha", "2026", 250);

        carro.mostrarDatos();

        System.out.println();

        moto.mostrarDatos();
    }
}
