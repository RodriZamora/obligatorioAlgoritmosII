package tads;

public class Cola<T> implements ICola<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;


    @Override
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        //pregunto si la lista es vacia
        if (fin == null) {
            inicio = nuevoNodo;
            fin = nuevoNodo;
        } else {
            //el ultimo nodo apunta al nuevo
            fin.setSig(nuevoNodo);
            //el nodo que ingreso ahora es el ultimo
            fin = nuevoNodo;
        }
    }

    @Override
    public T desencolar() {
        T aux = inicio.getDato();

        inicio = inicio.getSig();

        return aux;
    }

    @Override
    public T front() {
        return inicio.getDato();
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public T largo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
