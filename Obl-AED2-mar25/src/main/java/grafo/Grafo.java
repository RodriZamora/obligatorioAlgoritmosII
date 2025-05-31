package grafo;

import tads.Cola;
import tads.ILista;
import tads.Lista;

public class Grafo {
    private Conexion[][] conexiones;
    private Ciudades[] vertices;
    private int cantVertices;
    private final int maxVertices;
    private final boolean dirigido;


    public Grafo(int cantMaxVertices, boolean esDirigido) {
        cantVertices = 0;
        vertices = new Ciudades[cantMaxVertices];
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
            vertices[posLibre] = ciudades;
            cantVertices++;
        }
    }

    public void borrarVertice(Ciudades v) {
        int posVertice = obtenerPos(v);
        vertices[posVertice] = null;
        cantVertices--;

        for (int i = 0; i < conexiones.length; i++) {
            conexiones[posVertice][i].setExiste(false);  //Borro aristas adyacentes
            conexiones[i][posVertice].setExiste(false); //Borro aristas incidentes
        }
    }

    public boolean existe(Ciudades v) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].equals(v)) {
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
        System.out.println(vertices[pos]);
        visitados[pos] = true;
        for (int i = 0; i < conexiones.length; i++) {
            if (conexiones[pos][i].getExiste() && !visitados[i]) {
                dfsRecursivo(i, visitados);
            }
        }
    }

    public void bfs(Ciudades v) {
        int posV = obtenerPos(v);
        boolean[] visitados = new boolean[maxVertices];
        Cola<Integer> cola = new Cola<>();
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
    }

    public void bfsConEscalas(Ciudades v, int escala) {
        int posOrigen = obtenerPos(v);
        boolean[] visitados = new boolean[maxVertices];
        Cola<Tupla> cola = new Cola<>();

        visitados[posOrigen] = true;
        cola.encolar(new Tupla(posOrigen, 0));

        while (!cola.esVacia()) {

            Tupla tupla = cola.desencolar();
            if (tupla.getEscala() <= escala) {
                System.out.println(vertices[tupla.getPos()] + ", " + tupla.getEscala() + " escalas");
                for (int i = 0; i < conexiones.length; i++) {
                    if (conexiones[tupla.getPos()][i].getExiste() && !visitados[i]) {
                        visitados[i] = true;
                        cola.encolar(new Tupla(i, tupla.getEscala() + 1));
                    }
                }
            }
        }
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
                adyacentes.insertar(vertices[i]);
            }
        }
        return adyacentes;
    }

    //Metodos privados

    private int obtenerPosLibre() {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                return i;
            }
        }
        return -1; // No hay espacio libre
    }

    private int obtenerPos(Ciudades v) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].equals(v)) {
                return i;
            }
        }
        return -1; // No se encontró el vértice
    }


}
