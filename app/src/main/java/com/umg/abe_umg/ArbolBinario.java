package com.umg.abe_umg;

public class ArbolBinario 
{
    NodoArbol Raiz;
    
    public ArbolBinario()
    {
        Raiz = null;
    }
    
    public ArbolBinario(String cadena)
    {        
        Raiz = CrearArbolBE(cadena);
    }
    
    public void ReiniciaArbol()
    {
        Raiz = null;
    }
    
    public void CrearNodo(Object dato)
    {
        Raiz = new NodoArbol(dato);
    }
    
    public NodoArbol CrearSubArbol(NodoArbol dato2,NodoArbol dato1,NodoArbol operador)
    {
        operador.Izquierdo = dato1;
        operador.Derecho = dato2;
        return operador;
    }
    
    public boolean ArbolVacio()
    {
        return Raiz == null;
    }
    
    public int NodosCompletos(NodoArbol subArbol)
    {
        if (subArbol == null) 
        {
            return 0;
        }
        else
        {
            if (subArbol.Izquierdo != null && subArbol.Derecho != null) 
            {
                return NodosCompletos(subArbol.Izquierdo)+NodosCompletos(subArbol.Derecho)+1;
            }
            return NodosCompletos(subArbol.Izquierdo) + NodosCompletos(subArbol.Derecho);
        }
    }
    
    
    
    private String PreOrden(NodoArbol subArbol, String c)
    {
        String Cadena;
        Cadena = "";
        if (subArbol !=null) 
        {
            Cadena = c + subArbol.Dato.toString()+PreOrden(subArbol.Izquierdo, c)+PreOrden(subArbol.Derecho, c);
        }
        return Cadena;
    }
    
    private String InOrden(NodoArbol subArbol, String c)
    {
        String Cadena;
        Cadena = "";
        if (subArbol !=null) 
        {
            Cadena = c + InOrden(subArbol.Izquierdo, c) + subArbol.Dato.toString()+InOrden(subArbol.Derecho, c);
        }
        return Cadena;
    }
    
    private String PosOrden(NodoArbol subArbol, String c)
    {
        String Cadena;
        Cadena = "";
        if (subArbol !=null) 
        {
            Cadena = c + PosOrden(subArbol.Izquierdo, c) + PosOrden(subArbol.Derecho, c)+subArbol.Dato.toString();
        }
        return Cadena;
    }
    public String toString(int a)
    {
        String cadena = "";
        switch (a) 
        {
            case 0:
                cadena = PreOrden(Raiz, cadena);
                break;
            case 1:
                cadena = InOrden(Raiz, cadena);
                break;
            case 2:
                cadena = PosOrden(Raiz, cadena);
                break;            
        }
        return  cadena;
    }
    
    private int Prioridad(char c)
    {
        int p = 100;
        switch (c) 
        {
            case '^':
                p=30;
                break;
            case '*':               
            case '/':
                p=20;
                break;
            case '+':                
            case '-':
                p=10;
                break;
            default:
                p=0;
        }
        return  p;
    }
    
    private boolean  EsOperador(char c)
    {
        boolean Resultado;
        switch (c) 
        {
            case '(':
            case ')':
            case '^':
            case '*':
            case '/':
            case '+':
            case '-':  
                Resultado = true;
                break;
            default:
                Resultado = false;
        }
        return Resultado;
    }
    
    private NodoArbol CrearArbolBE(String cadena)
    {
        PilaArbol PilaOperadores;
        PilaArbol PilaExprexiones;
        
        NodoArbol token;
        NodoArbol op1;
        NodoArbol op2;
        NodoArbol op;
        boolean Bandera = false;
        
        PilaOperadores = new PilaArbol();
        PilaExprexiones = new PilaArbol();
        
        char caracterEvaluado;
        
        for (int i = 0; i < cadena.length(); i++) 
        {
            caracterEvaluado = cadena.charAt(i);
            token = new NodoArbol(caracterEvaluado);
            if (!EsOperador(caracterEvaluado))
            {
                if (!Bandera) 
                {
                    Bandera = true;                    
                    PilaExprexiones.Insertar(token);  
                }
                else
                {
                    String aux = PilaExprexiones.Quitar().Dato.toString();
                    aux = aux + caracterEvaluado;
                    token = new NodoArbol(aux);
                    PilaExprexiones.Insertar(token);
                }                           
            }
            else  //Es un Operador
            {
                Bandera = false;
                switch (caracterEvaluado)
                {
                    case '(':
                        PilaOperadores.Insertar(token);
                        break;
                    case ')':
                        while (!PilaOperadores.PilaVacia() && !PilaOperadores.TopePila().Dato.equals('(')) 
                        {                            
                            op2 = PilaExprexiones.Quitar();
                            op1 = PilaExprexiones.Quitar();
                            op = PilaOperadores.Quitar();
                            op = CrearSubArbol(op2, op1, op);
                            PilaExprexiones.Insertar(op);
                        }
                        PilaOperadores.Quitar(); //Quitamos de la pila el operador
                        break;
                    default:
                        while (!PilaOperadores.PilaVacia() && Prioridad(caracterEvaluado)<= Prioridad(PilaOperadores.TopePila().Dato.toString().charAt(0)))
                        {                            
                            op2 = PilaExprexiones.Quitar();
                            op1 = PilaExprexiones.Quitar();
                            op = PilaOperadores.Quitar();
                            op = CrearSubArbol(op2, op1, op);
                            PilaExprexiones.Insertar(op);
                        }
                    PilaOperadores.Insertar(token);
                }
            }
        }// Lee el siguiente caracter
        while (!PilaOperadores.PilaVacia())
        {            
            op2 = PilaExprexiones.Quitar();
            op1 = PilaExprexiones.Quitar();
            op = PilaOperadores.Quitar();
            op = CrearSubArbol(op2, op1, op);
            PilaExprexiones.Insertar(op);
        }
        op = PilaExprexiones.Quitar();
        return op;
    }
    
    public double EvaluaExpresion()
    {
        return Evalua(Raiz);
    }
    
    private double Evalua(NodoArbol subArbol)
    {
        double acum = 0;
        if (!EsOperador(subArbol.Dato.toString().charAt(0)))
        {
            return Double.parseDouble(subArbol.Dato.toString());
        }
        else
        {
            switch (subArbol.Dato.toString().charAt(0))
            {
                case '^':
                    acum = acum + Math.pow(Evalua(subArbol.Izquierdo), Evalua(subArbol.Derecho));
                    break;
                case '*':
                    acum = acum + Evalua(subArbol.Izquierdo) * Evalua(subArbol.Derecho);
                    break;
                case '/':
                    //Generar in if para validar que no sea 0
                    acum = acum + Evalua(subArbol.Izquierdo) / Evalua(subArbol.Derecho);
                    break;   
                case '+':
                    acum = acum + Evalua(subArbol.Izquierdo) + Evalua(subArbol.Derecho);
                    break;    
                case '-':
                    acum = acum + Evalua(subArbol.Izquierdo) - Evalua(subArbol.Derecho);
                    break;                   
            }
        }
        return acum;
    }
}
