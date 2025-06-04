package grafo;

import java.util.Objects;

public class Ciudad implements Comparable<Ciudad> {
    private String codigoCiudad;
    private String nombreCiudad;

    public Ciudad(String codigoCiudad, String nombreCiudad) {
        this.codigoCiudad = codigoCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    public Ciudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }


    public String getNombreCiudad() {
        return nombreCiudad;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(codigoCiudad, ciudad.codigoCiudad);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigoCiudad);
    }

    @Override
    public String toString() {
        return codigoCiudad + ";" + nombreCiudad;
    }

    @Override
    public int compareTo(Ciudad o) {
        if (o == null || getClass() != o.getClass()) return 0;
        Ciudad otraCiudad = (Ciudad) o;
        return this.getCodigoCiudad().compareTo(otraCiudad.getCodigoCiudad());
    }
}
