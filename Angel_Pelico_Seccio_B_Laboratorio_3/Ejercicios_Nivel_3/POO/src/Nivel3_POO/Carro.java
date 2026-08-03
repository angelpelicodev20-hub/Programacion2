package Nivel3_POO;

public class Carro implements Vehiculo {
    @Override
    public void acelerar() {
        System.out.println("El carro acelera");
    }

    @Override
    public void frenar() {
        System.out.println("El carro frena");
    }
}
