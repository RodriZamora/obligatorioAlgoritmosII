package grafo;

import dominio.Vuelo;
import interfaz.TipoVueloPermitido;
import tads.Cola;
import tads.Lista;

public class Grafo {
    private Conexion[][] conexiones;
    private Ciudad[] ciudades;
    private int cantVertices;
    private final int maxVertices;
    private final boolean dirigido;


    public Grafo(int cantMaxVertices, boolean esDirigido) {
        cantVertices = 0;
        ciudades = new Ciudad[cantMaxVertices];
        conexiones = new Conexion[cantMaxVertices][cantMaxVertices];
        if (esDirigido) {
            for (int i = 0; i < conexiones.length; i++) {
                for (int j = 0; j < conexiones.length; j++) {
                    conexiones[i][j] = new Conexion();
                }
            }
        } else {
            for (int i = 0; i < conexiones.length; i++) {
                for (int j = i; j < conexiones.length; j++) {
                    Conexion conexion = new Conexion();
                    conexiones[i][j] = conexion;
                    conexiones[j][i] = conexion; // Asignar la misma arista en ambas direcciones
                }
            }
        }
        maxVertices = cantMaxVertices;
        dirigido = esDirigido;
    }

    public void agregarVertice(Ciudad ciudades) {

        if (cantVertices < maxVertices) {
            int posLibre = obtenerPosLibre();
            this.ciudades[posLibre] = ciudades;
            cantVertices++;
        }
    }


    public boolean existe(Ciudad v) {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null && ciudades[i].equals(v)) {
                return true;
            }
        }
        return false;
    }

    public int cantidadVertices() {
        return cantVertices;
    }

    public void agregarConexion(Ciudad vInicio, Ciudad vFinal, Conexion conexion) {
        int posVinicial = obtenerPos(vInicio);
        int posVfinal = obtenerPos(vFinal);
        Conexion a = conexiones[posVinicial][posVfinal];
        a.setExiste(true);
    }

    public Conexion obtenerArista(Ciudad vInicio, Ciudad vFinal) {
        int posVinicial = obtenerPos(vInicio);
        int posVfinal = obtenerPos(vFinal);
        return conexiones[posVinicial][posVfinal];
    }


    public Boolean existeArista(Ciudad vInicio, Ciudad vFinal) {
        int posVinicial = obtenerPos(vInicio);
        int posVfinal = obtenerPos(vFinal);
        return conexiones[posVinicial][posVfinal].getExiste();
    }


    /// /////RECORRIDAS ////////////

    public Lista<Ciudad> bfsConEscalas(Ciudad c, int cant) {
        int posV = obtenerPos(c);
        boolean[] visitados = new boolean[maxVertices];
        int[] niveles = new int[maxVertices];
        Cola<Integer> cola = new Cola<>();
        Lista<Ciudad> result = new Lista<>();

        visitados[posV] = true;
        niveles[posV] = 0;
        cola.encolar(posV);

        while (!cola.esVacia()) {
            int pos = cola.desencolar();

            if (niveles[pos] <= cant) {
                result.agregarAlFinal(ciudades[pos]);
            }

            for (int i = 0; i < maxVertices; i++) {
                if (conexiones[pos][i] != null &&
                        conexiones[pos][i].getExiste() &&
                        !visitados[i] &&
                        conexiones[pos][i].getVuelos().largo() > 0) {

                    visitados[i] = true;
                    niveles[i] = niveles[pos] + 1;

                    if (niveles[i] <= cant) {
                        cola.encolar(i);
                    }
                }
            }
        }

        return result;
    }


    public String dijkstraConDestinoYCosto(Ciudad vOrigen, Ciudad vDestino, TipoVueloPermitido tipoVueloPermitido, double[] costoTotal) {
        int posOrigen = obtenerPos(vOrigen);
        int posDestino = obtenerPos(vDestino);

        boolean[] visitados = new boolean[maxVertices];
        double[] costos = new double[maxVertices];
        int[] anteriores = new int[maxVertices];


        for (int i = 0; i < maxVertices; i++) {
            costos[i] = Double.MAX_VALUE;
            anteriores[i] = -1;

        }
        costos[posOrigen] = 0;

        for (int i = 0; i < cantVertices; i++) {
            int posActual = obtenerVerticesNoVisitadoDeMenorCostoDouble(visitados, costos);

            if (posActual != -1) {
                visitados[posActual] = true;

                for (int j = 0; j < cantVertices; j++) {
                    if (!visitados[j]) {
                        Conexion arista = conexiones[posActual][j];
                        if (arista != null && arista.getExiste()) {

                            for (Vuelo vuelo : arista.getVuelos()) {
                                if (tipoVueloPermitido == TipoVueloPermitido.AMBOS
                                        || vuelo.getTipoDeVuelo().getTexto().equals(tipoVueloPermitido.getTexto())) {
                                    double nuevoCosto = costos[posActual] + vuelo.getMinutos();

                                    if (nuevoCosto < costos[j]) {
                                        // mejor costo: actualizamos todo
                                        costos[j] = nuevoCosto;
                                        anteriores[j] = posActual;

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (costos[posDestino] == Double.MAX_VALUE) {
            return null; // no hay camino
        }

        // reconstrucción del camino
        StringBuilder camino = new StringBuilder();
        int pos = posDestino;
        while (pos != -1) {
            camino.insert(0, ciudades[pos].getCodigoCiudad() + ";" + ciudades[pos].getNombreCiudad() + "|");
            pos = anteriores[pos];
        }

        if (camino.length() > 0) {
            camino.setLength(camino.length() - 1);
        }

        costoTotal[0] = costos[posDestino];
        return camino.toString();
    }


    public String dijkstraCostoDolares(Ciudad ciudadOrigen, Ciudad ciudadDestino, TipoVueloPermitido
            tipoVueloPermitido, double[] costoTotal) {
        int posOrigen = obtenerPos(ciudadOrigen);
        int posDestino = obtenerPos(ciudadDestino);

        boolean[] visitados = new boolean[maxVertices];
        double[] costos = new double[maxVertices];
        int[] anteriores = new int[maxVertices];


        for (int i = 0; i < maxVertices; i++) {
            costos[i] = Double.MAX_VALUE;
            anteriores[i] = -1;

        }
        costos[posOrigen] = 0;

        for (int i = 0; i < cantVertices; i++) {
            int posActual = obtenerVerticesNoVisitadoDeMenorCostoDouble(visitados, costos);

            if (posActual != -1) {
                visitados[posActual] = true;

                for (int j = 0; j < cantVertices; j++) {
                    if (!visitados[j]) {
                        Conexion arista = conexiones[posActual][j];
                        if (arista != null && arista.getExiste()) {

                            for (Vuelo vuelo : arista.getVuelos()) {
                                if (tipoVueloPermitido == TipoVueloPermitido.AMBOS
                                        || vuelo.getTipoDeVuelo().getTexto().equals(tipoVueloPermitido.getTexto())) {

                                    double nuevoCosto = costos[posActual] + vuelo.getCostoEnDolares();

                                    if (nuevoCosto < costos[j]) {
                                        // mejor costo: actualizamos todo
                                        costos[j] = nuevoCosto;
                                        anteriores[j] = posActual;

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (costos[posDestino] == Double.MAX_VALUE) {
            return null; // no hay camino
        }

        // reconstrucción del camino
        StringBuilder camino = new StringBuilder();
        int pos = posDestino;
        while (pos != -1) {
            camino.insert(0, ciudades[pos].getCodigoCiudad() + ";" + ciudades[pos].getNombreCiudad() + "|");
            pos = anteriores[pos];
        }

        if (camino.length() > 0) camino.setLength(camino.length() - 1);

        costoTotal[0] = costos[posDestino];
        return camino.toString();
    }


    private int obtenerVerticesNoVisitadoDeMenorCostoDouble(boolean[] visitados, double[] costos) {
        int posMin = -1;
        double min = Double.MAX_VALUE;

        for (int i = 0; i < maxVertices; i++) {
            if (!visitados[i] && costos[i] < min) {
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }


    //Metodos privados

    private int obtenerPosLibre() {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] == null) {
                return i;
            }
        }
        return -1; // No hay espacio libre
    }

    private int obtenerPos(Ciudad v) {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null && ciudades[i].equals(v)) {
                return i;
            }
        }
        return -1; // No se encontró el vértice
    }


    public Ciudad obtenerCiudad(String codigoCiudadOrigen) {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null && ciudades[i].getCodigoCiudad().equals(codigoCiudadOrigen)) {
                return ciudades[i];
            }
        }
        return null; // No se encontró el vértice
    }


}
