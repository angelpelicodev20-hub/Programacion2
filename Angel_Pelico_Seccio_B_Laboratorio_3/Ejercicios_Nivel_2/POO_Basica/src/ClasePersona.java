public class ClasePersona {

    //Atributos privados de la clase
    private String nombre;
    private String apellido;
    private int edad;

    //Getters y Setters para los atributos privados
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    //Validación de la edad para que sea mayor o igual a 18
    public void setEdad(int edad) {
        if (edad >= 18) {
            this.edad = edad;
        } else {
            System.out.println("La edad debe ser mayor o igual a 18");
            this.edad = 15;
        }
    }

    //Constructor de la clase que inicializa los atributos
    public ClasePersona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        setEdad(edad);
    }

    //Método para mostrar la información de la persona
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Edad: " + edad);
    }

    public static void main(String[] args) {
        ClasePersona persona = new ClasePersona("Juan", "Pérez", 25);
        persona.mostrarInformacion();

        // Intento de crear una persona con edad inválida
        System.out.println("\nIntentando crear una persona con edad inválida:");
        ClasePersona persona2 = new ClasePersona("Ana", "López", 15); // edad inválida
        persona2.mostrarInformacion();
    }
}
