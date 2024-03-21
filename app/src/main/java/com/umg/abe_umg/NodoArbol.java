package com.umg.abe_umg;

public class NodoArbol 
{
    Object Dato;
    NodoArbol Izquierdo;
    NodoArbol Derecho;
    
    public NodoArbol(Object x)
    {
        this.Dato = x;
        this.Izquierdo = null;
        this.Derecho = null;
    }
}
