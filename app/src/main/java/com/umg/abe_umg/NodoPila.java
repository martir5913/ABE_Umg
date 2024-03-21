package com.umg.abe_umg;

public class NodoPila 
{
    NodoArbol Dato;
    NodoPila Siguiente;
    
    public NodoPila(NodoArbol x)
    {
        this.Dato = x;
        this.Siguiente = null;
    }
}
