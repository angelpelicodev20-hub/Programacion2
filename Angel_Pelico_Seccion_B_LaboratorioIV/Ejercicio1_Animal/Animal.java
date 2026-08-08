package Ejercicio1_Animal;

public abstract class Animal {
    protected String nombre;

    public Animal(String nombre){
        this.nombre = nombre;
    }

    abstract void hacerSonido();

    public void verNombre(){
        System.out.println("Mi mascota se llama: " + nombre);
    }
}

class Perro extends Animal {

    public Perro(String nombre){
        super(nombre);
    }

    @Override
    void hacerSonido(){
        System.out.println(nombre + " dice: Gua Gua");
    }
}

class Gato extends Animal {

    public Gato(String nombre){
        super(nombre);
    }

    @Override
    void hacerSonido(){
        System.out.println(nombre + " dice: Miau Miau");
    }
}
