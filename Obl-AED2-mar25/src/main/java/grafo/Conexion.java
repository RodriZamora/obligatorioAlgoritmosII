package grafo;

import dominio.Vuelo;
import tads.Lista;

public class Conexion {
    private Boolean existe;
    private Lista<Vuelo> vuelos;

    public Conexion() {
        this.existe = false;
        this.vuelos = new Lista<>();
    }

    public void setExiste(Boolean existe) {
        this.existe = existe;
    }

    public Boolean getExiste() {
        return existe;
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.insertar(vuelo);
    }

    public boolean existeVuelo(String codigoDeVuelo) {
        boolean existeVuelo = false;
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCodigoDeVuelo().equals(codigoDeVuelo)) {
                existeVuelo = true;
            }
        }
        return existeVuelo;
    }
}
