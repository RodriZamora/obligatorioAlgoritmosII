package dominio;

import interfaz.TipoVuelo;

import java.util.Objects;

public class Vuelo implements Comparable<Vuelo> {

    private String codigoCiudadOrigen;
    private String codigoCiudadDestino;
    private String codigoDeVuelo;
    private Double combustible;
    private Double minutos;
    private Double costoEnDolares;
    private TipoVuelo tipoDeVuelo;

    public Vuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        this.codigoCiudadOrigen = codigoCiudadOrigen;
        this.codigoCiudadDestino = codigoCiudadDestino;
        this.codigoDeVuelo = codigoDeVuelo;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
        this.tipoDeVuelo = tipoDeVuelo;
    }

    public String getCodigoCiudadOrigen() {
        return codigoCiudadOrigen;
    }

    public void setCodigoCiudadOrigen(String codigoCiudadOrigen) {
        this.codigoCiudadOrigen = codigoCiudadOrigen;
    }

    public String getCodigoCiudadDestino() {
        return codigoCiudadDestino;
    }

    public void setCodigoCiudadDestino(String codigoCiudadDestino) {
        this.codigoCiudadDestino = codigoCiudadDestino;
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public void setCodigoDeVuelo(String codigoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;
    }

    public Double getCombustible() {
        return combustible;
    }

    public void setCombustible(Double combustible) {
        this.combustible = combustible;
    }

    public Double getMinutos() {
        return minutos;
    }

    public void setMinutos(Double minutos) {
        this.minutos = minutos;
    }

    public Double getCostoEnDolares() {
        return costoEnDolares;
    }

    public void setCostoEnDolares(Double costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public TipoVuelo getTipoDeVuelo() {
        return tipoDeVuelo;
    }

    public void setTipoDeVuelo(TipoVuelo tipoDeVuelo) {
        this.tipoDeVuelo = tipoDeVuelo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return Objects.equals(codigoDeVuelo, vuelo.codigoDeVuelo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigoDeVuelo);
    }

    @Override
    public int compareTo(Vuelo o) {
        return this.codigoDeVuelo.compareTo(o.codigoDeVuelo);
    }
}
