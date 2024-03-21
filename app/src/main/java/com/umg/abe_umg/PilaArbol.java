package com.umg.abe_umg;
public class PilaArbol 
{
    private NodoPila Tope;
    
    public PilaArbol()
    {
        this.Tope = null;
    }
    
    public void Insertar(NodoArbol elemento)
    {
        NodoPila Nuevo;
        
        Nuevo = new NodoPila(elemento);
        Nuevo.Siguiente = Tope;
        Tope = Nuevo;
    }
    
    public boolean PilaVacia()
    {
        return  Tope == null;
    }
    
    public NodoArbol TopePila()
    {
        return Tope.Dato;
    }
    
    public void ReiniciarPila()
    {
        Tope= null;
    }
    
    public NodoArbol Quitar()
    {
        NodoArbol aux = null;
        if (!PilaVacia())
        {
            aux = Tope.Dato;
            Tope = Tope.Siguiente;
        }
        return aux;
    }
}
