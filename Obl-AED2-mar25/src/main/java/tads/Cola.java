package tads;

public class Cola<T> implements ICola<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cant;


    @Override
    public void encolar(T dato) {
        if (fin == null)
            fin = inicio = new Nodo<T>(dato);
        else {
            fin.setSig(new Nodo<T>(dato));
            fin = fin.getSig();
        }
        cant++;
    }

    @Override
    public T desencolar() {
        T dato = inicio.getDato();
        inicio = inicio.getSig();
        if (inicio == null)
            fin = null;
        cant--;
        return dato;
    }

    @Override
    public T front() {
        return inicio.getDato();
    }

    @Override
    public boolean esVacia() {
        return cant == 0;
    }

    @Override
    public int largo() {
        return cant;
    }

}
