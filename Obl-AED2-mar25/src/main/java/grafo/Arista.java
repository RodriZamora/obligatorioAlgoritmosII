package grafo;

import dominio.Vuelo;

public class Arista {
    private String peso;
    private Boolean existe;

    public Arista(String peso) {
        this.peso = peso;
        this.existe = true;
    }

    public Arista() {
        this.existe = false;
    }

    public void setExiste(Boolean existe) {
        this.existe = existe;
    }

    public Boolean getExiste() {
        return existe;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    /*public boolean existeVuelo(String codigoDeVuelo) {

    }*/

    public void agregarVuelo(Vuelo vuelo) {
    }
}
