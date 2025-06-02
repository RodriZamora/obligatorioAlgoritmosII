package tads;


import grafo.Ciudades;

import java.util.Comparator;
import java.util.Iterator;

public class Lista<T extends Comparable<T>> implements ILista<T>, Iterable<T> {

    protected NodoLista<T> inicio;
    protected int largo;

    public Lista() {
        this.inicio = null;
        this.largo = 0;
    }

    @Override
    public void insertar(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }

    @Override
    public void borrar(T dato) {

    }

    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean existe(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    @Override
    public T recuperar(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return aux.getDato();
            }
            aux = aux.getSig();
        }
        return null;
    }

    @Override
    public T recuperar(int pos) {
        if (pos < 0 || pos >= largo) {
            return null;
        }
        NodoLista<T> aux = inicio;
        for (int i = 0; i < pos; i++) {
            aux = aux.getSig();
        }
        return aux.getDato();
    }


    @Override
    public boolean esVacia() {
        return largo == 0;
    }

    @Override
    public boolean esLlena() {
        return false;
    }

    @Override
    public void imprimirDatos() {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getSig() != null) {
                System.out.print(aux.getDato() + " -> ");
            } else {
                System.out.print(aux.getDato());
            }
            aux = aux.getSig();
        }
        System.out.println();
    }

    public void imprimirDatosV2(NodoLista<T> nodo) {
        if (nodo != null) {
            System.out.println(nodo.getDato());
            imprimirDatosV2(nodo.getSig());
        }
    }


    public void ordenarLexicograficamentePorCodigo() {
        if (inicio == null || inicio.getSig() == null) return;

        boolean huboCambios;
        do {
            huboCambios = false;
            NodoLista<T> actual = inicio;
            while (actual != null && actual.getSig() != null) {
                if (actual.getDato().compareTo(actual.getSig().getDato()) > 0) {
                    T aux = actual.getDato();
                    actual.setDato(actual.getSig().getDato());
                    actual.getSig().setDato(aux);
                    huboCambios = true;
                }
                actual = actual.getSig();
            }
        } while (huboCambios);
    }


    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.dato;
                aux = aux.sig;
                return dato;
            }

            @Override
            public void remove() {
            }

        };
    }

    @Override
    public void agregarAlFinal(T elemento) {

        if (esVacia() || elemento.compareTo(inicio.getDato()) < 0) {
            inicio = new NodoLista<>(elemento, inicio);
        } else {
            NodoLista<T> aux = inicio;
            while (aux.getSig() != null && aux.getSig().getDato().compareTo(elemento) < 0) {
                aux = aux.getSig();
            }
            NodoLista<T> nuevo = new NodoLista<>(elemento, aux.getSig());
            aux.setSig(nuevo);
        }
        this.largo++;
    }

    public NodoLista<T> getInicio() {
        return inicio;
    }

    public T obtenerPorIndice(int i) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(i)) {
                return aux.getDato();
            }
            aux = aux.getSig();
        }
        return null;
    }


    class NodoLista<T> {
        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        public void setSig(NodoLista<T> sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }
    }


}
