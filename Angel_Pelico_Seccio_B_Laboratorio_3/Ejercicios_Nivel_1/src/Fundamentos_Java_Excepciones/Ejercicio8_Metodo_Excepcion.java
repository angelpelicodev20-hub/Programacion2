package Fundamentos_Java_Excepciones;

public class Ejercicio8_Metodo_Excepcion {
    public static void main(String[] args) {
        String[] entradas = {"123", "abc", "45.6", "", "  78  ", "999999999999"};

        for (String entrada : entradas) {
            convertir(entrada);
        }
    }

        public static void convertir(String texto) {
        try {
            int numero = Integer.parseInt(texto.trim());
            System.out.println("'" + texto + "' -> Conversión exitosa: " + numero);

        } catch (NumberFormatException e) {
            System.out.println("'" + texto + "' -> Error: no se pudo convertir a número.");

        } catch (NullPointerException e) {
            System.out.println("Error: el texto es null.");
        }
    }
}
