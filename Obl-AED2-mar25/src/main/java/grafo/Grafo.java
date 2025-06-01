package grafo;

import dominio.Ciudad;
import tads.Cola;
import tads.ICola;
import tads.ILista;
import tads.Lista;

public class Grafo {
    private Conexion[][] conexiones;
    private Ciudades[] ciudades;
    private int cantVertices;
    private final int maxVertices;
    private final boolean dirigido;


    public Grafo(int cantMaxVertices, boolean esDirigido) {
        cantVertices = 0;
        ciudades = new Ciudades[cantMaxVertices];
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

    public void agregarVertice(Ciudades ciudades) {

        if (cantVertices < maxVertices) {
            int posLibre = obtenerPosLibre();
            this.ciudades[posLibre] = ciudades;
            cantVertices++;
        }
    }

    public void borrarVertice(Ciudades v) {
        int posVertice = obtenerPos(v);
        ciudades[posVertice] = null;
        cantVertices--;

        for (int i = 0; i < conexiones.length; i++) {
            conexiones[posVertice][i].setExiste(false);  //Borro aristas adyacentes
            conexiones[i][posVertice].setExiste(false); //Borro aristas incidentes
        }
    }

    public boolean existe(Ciudades v) {
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

    public void agregarConexion(Ciudades vInicio, Ciudades vFinal, Conexion conexion) {
        int posVinicial = obtenerPos(vInicio);
        int posVfinal = obtenerPos(vFinal);
        Conexion a = conexiones[posVinicial][posVfinal];
        a.setExiste(true);
    }

    public void borrarArista(Ciudades vInicio, Ciudades vFinal) {
        int posVinicial = obtenerPos(vInicio);
        int posVfinal = obtenerPos(vFinal);

        conexiones[posVinicial][posVfinal].setExiste(false);
    }

    public Conexion obtenerArista(Ciudades vInicio, Ciudades vFinal) {
        int posVinicial = obtenerPos(vInicio);
        int posVfinal = obtenerPos(vFinal);
        return conexiones[posVinicial][posVfinal];
    }


    public Boolean existeArista(Ciudades vInicio, Ciudades vFinal) {
        int posVinicial = obtenerPos(vInicio);
        int posVfinal = obtenerPos(vFinal);
        return conexiones[posVinicial][posVfinal].getExiste();
    }


    /// /////RECORRIDAS ////////////

    /*public void dfs(Ciudades v) {
        boolean[] visitados = new boolean[maxVertices];
        int pos = obtenerPos(v);
        dfsRec(pos, visitados);
    }

    private void dfsRec(int pos, boolean[] visitados) {
        System.out.println(vertices[pos]);
        visitados[pos] = true;

        for (int i = 0; i < conexiones.length; i++) {
            if (conexiones[pos][i].getExiste() && !visitados[i]) {
                dfsRec(i, visitados);
            }
        }
    }

    public void bfs(Ciudades v) {
        int posV = obtenerPos(v);
        boolean[] visitados = new boolean[maxVertices];
        ICola<Integer> cola = new Cola<>();

        visitados[posV] = true;
        cola.encolar(posV);

        while (!cola.esVacia()) {
            int pos = cola.desencolar();
            System.out.println(vertices[pos] + " ");
            for (int i = 0; i < conexiones.length; i++) {
                if (conexiones[pos][i].getExiste() && !visitados[i]) {
                    visitados[i] = true;
                    cola.encolar(i);
                }
            }
        }

    }*/
    public void dfs(Ciudades v) {
        boolean[] visitados = new boolean[maxVertices];
        int pos = obtenerPos(v);
        dfsRecursivo(pos, visitados);
    }

    private void dfsRecursivo(int pos, boolean[] visitados) {
        System.out.println(ciudades[pos]);
        visitados[pos] = true;
        for (int i = 0; i < conexiones.length; i++) {
            if (conexiones[pos][i].getExiste() && !visitados[i]) {
                dfsRecursivo(i, visitados);
            }
        }
    }

    /*public String bfsConEscalas(Ciudades v, int cant) {
        int posV = obtenerPos(v);
        String valorString = "";
        boolean[] visitados = new boolean[maxVertices];
        int[] niveles = new int[maxVertices];
        Cola<Integer> cola = new Cola<>();
        Lista<Ciudades> result = new Lista<>();

        visitados[posV] = true;
        niveles[posV] = 0;
        cola.encolar(posV);

        while (!cola.esVacia()) {
            int pos = cola.desencolar();

            if (niveles[pos] <= cant) {
                result.agregarAlFinal(ciudades[pos]);
            }

            for (int i = 0; i < maxVertices; i++) {
                if (conexiones[pos][i] != null && conexiones[pos][i].getExiste() && !visitados[i] && conexiones[pos][i].getVuelos().largo() > 0) {
                    visitados[i] = true;
                    niveles[i] = niveles[pos] + 1;
                    if (niveles[i] <= cant) {
                        cola.encolar(i);
                    }
                }
            }
        }

        for (int i = 0; i < result.largo(); i++) {
            Ciudades ciudad = result.recuperar(i);
            if (ciudad != null) {
                valorString += ciudad.toString();
                if (i < result.largo() - 1) {
                    valorString += "|";
                }
            }
        }
        return valorString;
    }*/

    public Lista<Ciudades> bfsConEscalas(Ciudades v, int cant) {
        int posV = obtenerPos(v);
        boolean[] visitados = new boolean[maxVertices];
        int[] niveles = new int[maxVertices];
        Cola<Integer> cola = new Cola<>();
        Lista<Ciudades> result = new Lista<>();

        visitados[posV] = true;
        niveles[posV] = 0;
        cola.encolar(posV);

        while (!cola.esVacia()) {
            int pos = cola.desencolar();

            // ✅ Igual que el método original: incluye origen (nivel 0) hasta nivel 'cant'
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


    public void dijkstra(Ciudades vOrigen) {
        int posOrigen = obtenerPos(vOrigen);
        boolean[] visitados = new boolean[maxVertices];
        int[] costos = new int[maxVertices];
        int[] anteriores = new int[maxVertices];

        for (int i = 0; i < maxVertices; i++) {
            costos[i] = Integer.MAX_VALUE; // Inicializar costos a infinito
            anteriores[i] = -1; // No hay anterior al inicio
            visitados[i] = false; // Ningún vértice visitado

        }

        costos[posOrigen] = 0; // El costo al origen es 0
        for (int v = 0; v < cantVertices; v++) {
            int pos = obtenerVerticeNoVisitadoDeMenorCosto(visitados, costos);

            if (pos != -1) {
                //Lo hago para los vertices que puedo llegar desde la salida
                visitados[pos] = true;

                for (int i = 0; i < conexiones.length; i++) {
                    if (conexiones[pos][i].getExiste() && !visitados[i]) {
                        int distanciaNueva = costos[pos] + conexiones[pos][i].getPonderacion();
                        if (distanciaNueva < costos[i]) {
                            costos[i] = distanciaNueva;
                            anteriores[i] = pos; // Guardar el vértice anterior
                        }
                    }
                }
            }
        }
    }

    private int obtenerVerticeNoVisitadoDeMenorCosto(boolean[] visitados, int[] costos) {
        int posMin = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < maxVertices; i++) {
            if (!visitados[i] && costos[i] < min) {
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }


    public ILista<Ciudades> adyacentes(Ciudades ciudades) {
        int pos = obtenerPos(ciudades);
        ILista<Ciudades> adyacentes = new Lista<>();
        for (int i = 0; i < conexiones.length; i++) {
            if (conexiones[pos][i].getExiste()) {
                adyacentes.insertar(this.ciudades[i]);
            }
        }
        return adyacentes;
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

    private int obtenerPos(Ciudades v) {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null && ciudades[i].equals(v)) {
                return i;
            }
        }
        return -1; // No se encontró el vértice
    }


    public Ciudades obtenerCiudad(String codigoCiudadOrigen) {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null && ciudades[i].getCodigoCiudad().equals(codigoCiudadOrigen)) {
                return ciudades[i];
            }
        }
        return null; // No se encontró el vértice
    }
}
