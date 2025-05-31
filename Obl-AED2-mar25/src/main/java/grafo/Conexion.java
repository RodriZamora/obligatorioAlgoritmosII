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

    public Lista<Vuelo> getVuelos() {
        return vuelos;
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
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCodigoDeVuelo().equals(codigoDeVuelo)) {
                return true;
            }
        }
        return false;
    }
}
