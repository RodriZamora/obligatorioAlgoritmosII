package grafo;

import dominio.Vuelo;
import interfaz.TipoVuelo;
import tads.Lista;

public class Conexion {
    private Boolean existe;
    private int ponderacion;
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

    public int getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(int ponderacion) {
        this.ponderacion = ponderacion;
    }

    public boolean existeVuelo(String codigoDeVuelo) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCodigoDeVuelo().equals(codigoDeVuelo)) {
                return true;
            }
        }
        return false;
    }

    public void actualizarVuelo(String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCodigoDeVuelo().equals(codigoDeVuelo)) {
                vuelo.setCombustible(combustible);
                vuelo.setMinutos(minutos);
                vuelo.setCostoEnDolares(costoEnDolares);
                vuelo.setTipoDeVuelo(tipoDeVuelo);
                return;
            }
        }
    }


}
