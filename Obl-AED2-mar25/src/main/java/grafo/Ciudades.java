package grafo;

import java.util.Objects;

public class Ciudades {
    private String id;

    public Ciudades(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ciudades ciudades = (Ciudades) o;
        return Objects.equals(id, ciudades.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    @Override
    public String toString() {
        return "Vertice{" + "id='" + id + '\'' + '}';
    }
}
