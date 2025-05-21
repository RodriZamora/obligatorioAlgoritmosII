package abb;

import java.util.Comparator;

public class ABB<T extends Comparable<T>> {
    private NodoABB<T> raiz;

    private final Comparator<T> comparador;

    public ABB() {
        comparador = null;
    }

    public ABB(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    private int comparar(T dato1, T dato2) {
        if (this.comparador == null) {
            return dato1.compareTo(dato2);
        }
        return this.comparador.compare(dato1, dato2);
    }

    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new NodoABB<>(dato);
        } else {
            insertar(raiz, dato);
        }
    }

    private void insertar(NodoABB<T> nodo, T dato) {
        if (comparar(dato, nodo.dato) < 0) {
            if (nodo.izq == null) {
                nodo.izq = new NodoABB<>(dato);
            } else {
                insertar(nodo.izq, dato);
            }
        } else {
            if (nodo.der == null) {
                nodo.der = new NodoABB<>(dato);
            } else {
                insertar(nodo.der, dato);
            }
        }
    }

    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(NodoABB<T> nodo) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.izq == null && nodo.der == null) {
            return 0;
        }
        return 1 + Math.max(alturaRec(nodo.izq), alturaRec(nodo.der));
    }

    public T existe(T viajero) {
        return existeRec(raiz, viajero);
    }

    private T existeRec(NodoABB<T> nodo, T viajero) {
        if (nodo != null) {
            if (viajero.equals(nodo.dato)) {
                return nodo.dato;
            } else if (viajero.compareTo(nodo.dato) < 0) {
                return existeRec(nodo.izq, viajero);
            } else {
                return existeRec(nodo.der, viajero);
            }
        }
        return null;
    }

    public T existeConContador(T viajero, int[] contador) {
        return existeConContadorRec(raiz, viajero, contador);
    }

    private T existeConContadorRec(NodoABB<T> nodo, T viajero, int[] contador) {
        if (nodo != null) {
            contador[0]++;

            if (viajero.equals(nodo.dato)) {
                return nodo.dato;
            } else if (viajero.compareTo(nodo.dato)< 0) {
                return existeConContadorRec(nodo.izq, viajero, contador);
            } else {
                return existeConContadorRec(nodo.der, viajero, contador);
            }
        }
        return null;
    }

    public String listarAscendente() {
        String viajerosListados = "";
        return listarAscendenteRec(raiz, viajerosListados);
    }

    private String listarAscendenteRec(NodoABB<T> nodo, String viajerosListados) {
        if (nodo != null) {
            viajerosListados = listarAscendenteRec(nodo.izq, viajerosListados);
            if (!viajerosListados.isEmpty()) {
                viajerosListados += "|";
            }
            viajerosListados += nodo.dato.toString();
            viajerosListados = listarAscendenteRec(nodo.der, viajerosListados);
        }
        return viajerosListados;
    }

    public String listarDescendente() {
        String viajerosListados = "";
        return listarDescendenteRec(raiz, viajerosListados);
    }

    private String listarDescendenteRec(NodoABB<T> nodo, String viajerosListados) {
        if (nodo != null) {
            viajerosListados = listarDescendenteRec(nodo.der, viajerosListados);
            if (!viajerosListados.isEmpty()) {
                viajerosListados += "|";
            }
            viajerosListados += nodo.dato.toString();
            viajerosListados = listarDescendenteRec(nodo.izq, viajerosListados);
        }
        return viajerosListados;
    }


    private class NodoABB<Q> {
        private Q dato;
        private NodoABB<Q> izq;
        private NodoABB<Q> der;

        public NodoABB(Q dato) {
            this.dato = dato;
        }

        @Override
        public String toString() {
            return "NodoABB{" + dato + '}';
        }
    }
}
