package Nivel3_POO;

import java.util.ArrayList;
import java.util.List;

public class DemoPOO {
    public static void main(String[] args) {
        System.out.println("=== Herencia ===");
        Animal perro = new Perro();
        perro.sonido();

        System.out.println("\n=== Polimorfismo ===");
        List<Animal> animales = new ArrayList<>();
        animales.add(new Perro());
        animales.add(new Gato());

        for (Animal animal : animales) {
            animal.sonido();
        }

        System.out.println("\n=== Abstracción ===");
        List<Figura> figuras = new ArrayList<>();
        figuras.add(new Cuadrado(4));
        figuras.add(new Circulo(3));

        for (Figura figura : figuras) {
            System.out.println("Área: " + figura.area());
        }

        System.out.println("\n=== Interfaces ===");
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Carro());
        vehiculos.add(new Moto());

        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.acelerar();
            vehiculo.frenar();
        }
    }
}
