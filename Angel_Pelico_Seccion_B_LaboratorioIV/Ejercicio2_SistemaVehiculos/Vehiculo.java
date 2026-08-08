package Ejercicio2_SistemaVehiculos;

public class Vehiculo {
    private String marca;
    private String Modelo;
    private int Cilindraje;

    public Vehiculo(String marca, String Modelo, int Cilindraje){
        this.marca = marca;
        this.Modelo = Modelo;
        this.Cilindraje = Cilindraje;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return Modelo;
    }

    public int getCilindraje(){
        return Cilindraje;
    }

    public void mostrarDatos(){
        System.out.println("Marca del vehiculo: " + marca);
        System.out.println("Modelo del vehiculo: " + Modelo);
        System.out.println("Cilindraje del vehiculo: " + Cilindraje);
    }
}

class Carro extends Vehiculo{
    public Carro(String marca, String Modelo, int Cilindraje){
        super(marca, Modelo, Cilindraje);
    }

    @Override
    public void mostrarDatos(){
        System.out.println("Datos del vehiculo: " + "Marca: " + getMarca() + " ,Modelo: " + getModelo() + " ,Cilindraje: " + getCilindraje());
    }
}

class Moto extends Vehiculo{
    public Moto(String marca, String Modelo, int Cilindraje){
        super(marca, Modelo, Cilindraje);
    }

    @Override
    public void mostrarDatos(){
        System.out.println("Datos del vehiculo: " + "Marca: " + getMarca() + " ,Modelo: " + getMarca() + " ,Cilindraje: " + getCilindraje());
    }
}



