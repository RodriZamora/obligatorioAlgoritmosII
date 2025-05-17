package dominio;

import java.util.Comparator;

public class ComparadorPorCorreo implements Comparator<Viajero> {
    @Override
    public int compare(Viajero v1, Viajero v2) {
        return v1.getCorreo().compareTo(v2.getCorreo());
    }
}
