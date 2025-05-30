package grafo;

import tads.Cola;
import tads.ICola;
import tads.ILista;
import tads.Lista;

public class Grafo {
    private Conexion[][] conexiones;
    private Ciudades[] vertices;
    private int cantidad;
    private final int maxVertices;
    private final boolean dirigido;


    public Grafo(int cantMaxVertices, boolean esDirigido) {
        cantidad = 0;
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

        if (cantidad < maxVertices) {
            int posLibre = obtenerPosLibre();
            vertices[posLibre] = ciudades;
            cantidad++;
        }
    }

    public void borrarVertice(Ciudades v) {
        int posVertice = obtenerPos(v);
        vertices[posVertice] = null;
        cantidad--;

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
        return cantidad;
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


    public Boolean existeArista(Ciudades vInicio, Ciudades vFinal){
        int posVinicial = obtenerPos(vInicio);
        int posVfinal = obtenerPos(vFinal);
        return conexiones[posVinicial][posVfinal].getExiste();
    }


    /// /////RECORRIDAS ////////////

    public void dfs(Ciudades v) {
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
