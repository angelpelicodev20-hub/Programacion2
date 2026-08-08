package Ejercicio4_SistemaBiblioteca;

public abstract class Material {
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public Material(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }

    public void mostrarInformacion() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año: " + anioPublicacion);
    }
}

class Libro extends Material {
    private int numPaginas;

    public Libro(String titulo, String autor, int anioPublicacion, int numPaginas) {
        super(titulo, autor, anioPublicacion);
        this.numPaginas = numPaginas;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Libro");
        System.out.println("Número de páginas: " + numPaginas);
    }
}

class Revista extends Material {
    private int numeroEdicion;

    public Revista(String titulo, String autor, int anioPublicacion, int numeroEdicion) {
        super(titulo, autor, anioPublicacion);
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Revista");
        System.out.println("Edición N°: " + numeroEdicion);
    }
}