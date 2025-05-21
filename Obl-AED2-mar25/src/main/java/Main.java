import abb.ABB;
import dominio.Viajero;
import dominio.ViajeroWrapper;
import interfaz.Categoria;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Viajero[] viajerosList = {
                new Viajero("4.745.897-9", "MaxVerstappen", "verstappen@ort.uy", 27, Categoria.PLATINO),
                new Viajero("4.741.897-9", "Hamilton", "hamilton@ort.uy",40, Categoria.FRECUENTE),
                new Viajero("4.747.897-9", "Leclerc", "leclerc@ort.uy",27, Categoria.ESTANDAR),
                new Viajero("4.743.897-9", "Norris", "blandonorris@ort.uy",26, Categoria.ESTANDAR),
        };

        ABB<Viajero> viajeros = new ABB<>();
        for(Viajero v : viajerosList) {
            viajeros.insertar(v);
        }

        System.out.println("Viajeros: ");
        System.out.println(viajeros.listarAscendente());
        System.out.println();

        ABB< ViajeroWrapper> viajerosWrapper = new ABB<>();
        for(Viajero v : viajerosList){
            ViajeroWrapper vw = new ViajeroWrapper(v);
            viajerosWrapper.insertar(vw);
        }
        System.out.println("Viajeros Wrapper: ");
        System.out.println(viajerosWrapper.listarAscendente());
        System.out.println();


        System.out.println("Buscando a Max Verstappen: ");
        Viajero v = new Viajero("4.745.897-9", "", "", 0, null);
        int[] contador = new int[1];
        System.out.println(viajeros.existeConContador(v, contador));

        System.out.println("Buscando por Mail a Max:");
        ViajeroWrapper vw = new ViajeroWrapper("verstappen@ort.uy");
        System.out.println(viajerosWrapper.existeConContador(vw, contador));


    }
}
