package Ejercicio4_SistemaBiblioteca;

public class Biblioteca_ClasePrincipal {
    public static void main(String[] args) {
        Material[] materiales = {
            new Libro("Cien Años de Soledad", "Gabriel García Márquez", 1967, 471),
            new Revista("National Geographic", "Varios Autores", 2024, 305)
        };

        for (Material m : materiales) {
            m.mostrarInformacion();
            System.out.println();
        }
    }
}
