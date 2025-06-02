package grafo;

import java.util.Objects;

public class Ciudades implements Comparable<Ciudades> {
    private String codigoCiudad;
    private String nombreCiudad;

    public Ciudades(String codigoCiudad, String nombreCiudad) {
        this.codigoCiudad = codigoCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    public Ciudades(String codigoCiudad) {
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
        Ciudades ciudades = (Ciudades) o;
        return Objects.equals(codigoCiudad, ciudades.codigoCiudad);
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
    public int compareTo(Ciudades o) {
        if (o == null || getClass() != o.getClass()) return 0;
        Ciudades otraCiudad = (Ciudades) o;
        return this.getCodigoCiudad().compareTo(otraCiudad.getCodigoCiudad());
    }
}
