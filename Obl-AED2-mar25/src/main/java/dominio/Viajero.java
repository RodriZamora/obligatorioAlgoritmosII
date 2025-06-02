package dominio;

import interfaz.Categoria;

import java.util.Objects;

public class Viajero implements Comparable<Viajero> {
    private String cedula;
    private int cedulaSanitizada;
    private String nombre;
    private String correo;
    private int edad;
    private Categoria categoria;

    public Viajero(String cedula, int cedulaSanitizada, String nombre, String correo, int edad, Categoria categoria) {
        this.cedula = cedula;
        this.cedulaSanitizada = cedulaSanitizada;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.categoria = categoria;
    }

    public Viajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.categoria = categoria;
    }

    public Viajero(int cedulaSanitizada) {
        this.cedulaSanitizada = cedulaSanitizada;
    }


    public String getCorreo() {
        return correo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viajero viajero = (Viajero) o;
        return cedulaSanitizada == viajero.cedulaSanitizada;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cedulaSanitizada);
    }

    @Override
    public int compareTo(Viajero o) {
        return Integer.compare(this.cedulaSanitizada, o.cedulaSanitizada);
    }

    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + correo + ";" + edad + ";" + categoria.getTexto();
    }
}
