package Ejercicio1_Animal;

public class Ejercicio1_ClasePrincipal {
    public static void main(String[] args) {
        Perro perro = new Perro("Chispa");
        Gato gato = new Gato("Cholito");

        perro.verNombre();
        gato.verNombre();

        System.out.println();

        perro.hacerSonido();
        gato.hacerSonido();
    }
}
