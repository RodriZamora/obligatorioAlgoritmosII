package dominio;

import interfaz.Categoria;

import java.util.Objects;

public class Viajero implements Comparable<Viajero> {
    private String cedula;
    private String nombre;
    private String correo;
    private int edad;
    private Categoria categoria;

    public Viajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.categoria = categoria;
    }

    public Viajero(String correo){
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Viajero viajero = (Viajero) o;
        return Objects.equals(cedula, viajero.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cedula);
    }

    @Override
    public int compareTo(Viajero o) {
        return this.cedula.compareTo(o.cedula);
    }

    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + correo + ";" + edad + ";" + categoria;
    }
}
