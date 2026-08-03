package Nivel3_POO;

public class Moto implements Vehiculo {
    @Override
    public void acelerar() {
        System.out.println("La moto acelera");
    }

    @Override
    public void frenar() {
        System.out.println("La moto frena");
    }
}
