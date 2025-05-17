package abb;

import lista.ILista;
import lista.Lista;

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
        if (comparar(nodo.dato, dato) < 0) {
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
        if (nodo == null) {
            return null;
        }
        if (comparar(nodo.dato, viajero) == 0) {
            return nodo.dato;
        }
        if (comparar(nodo.dato, viajero) < 0) {
            return existeRec(nodo.izq, viajero);
        } else {
            return existeRec(nodo.der, viajero);
        }
    }


    //Es una buena practica no usar el mismo tipo, no estan obligados a ser la misma.
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
