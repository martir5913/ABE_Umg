package com.umg.abe_umg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Arbol extends AppCompatActivity
{
    //Variables Globales
    private ArbolBinario Arbol;
    ArbolBinario ABE;
    private String datos;
    public void setArbol(ArbolBinario arbol)
    {
        //Con los valores traidos del activity padre se setea el arbol binario
        this.Arbol = arbol;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbol);
        //Se validan los datos otorgados por el activity padre los cuales se valida si son null o si traen datos
        Bundle parametros = this.getIntent().getExtras();
        if (parametros!=null)
        {
            datos = parametros.getString("cadena");
        }
        ConstraintLayout layout1 = findViewById(R.id.main);
        Lienzo1 fondo = new Lienzo1(this); //Se gener a un objeto de tipo lienzo
        layout1.addView(fondo);
    }
    class Lienzo1 extends View
    {
        private final Paint mPaint;
        private Paint nodoPaint;
        private Paint linePaint;
        private int nodoRadio = 50; // Tamaño del nodo
        public Lienzo1(Context context)
        {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(Color.WHITE );
            mPaint.setStrokeWidth(5);
            init();
        }
        private void init()
        {
            nodoPaint = new Paint();
            nodoPaint.setTextSize(40); // Ajusta el tamaño del texto
            nodoPaint.setStyle(Paint.Style.FILL);
            nodoPaint.setColor(Color.rgb( 60, 63, 64  )); // Cambia el color del Circulo
            //Otros atributos de inicialización
            linePaint = new Paint();
            linePaint.setColor(Color.rgb( 60, 63, 64 )); //Cambia el color de la línea
            linePaint.setStrokeWidth(3);
        }
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            ABE = new ArbolBinario(datos);
            setArbol(ABE);
            //drawTree(canvas,getWidth() / 2,100,Arbol.Raiz);
            drawTree(canvas, Arbol.Raiz, getWidth() / 2, 50, getWidth() / 4, 0);
        }
        private void drawTree(Canvas canvas, NodoArbol nodo, int x, int y, int xOffset, int depth)
        {
            //La variable xOffset es la compensacion en el eje X
            //La variable depth es la profundidad de los niveles en el arbol
            if (nodo == null)
                return; //Si el arbol es null genera un return y no sigue el codigo

            // Dibuja el nodo actual
            canvas.drawCircle(x, y, nodoRadio, nodoPaint);
            String text = String.valueOf(nodo.Dato);
            Rect bounds = new Rect(); //Se crea un objeto de tipo Rect que puede calcular las dimensiones de un rectangulo

            nodoPaint.setColor(Color.WHITE); // Cambia el color del texto a blanco
            nodoPaint.getTextBounds(text, 0, text.length(), bounds);//Con el objeto bounds obtenemos las dimensiones del texto para acomodarlo en el dibujado del circulo
            canvas.drawText(text, x - bounds.exactCenterX(), y - bounds.exactCenterY(), nodoPaint);
            init();

            // Dibuja las líneas a los hijos y luego dibuja los hijos
            if (nodo.Izquierdo != null)
            {
                int childX = x - xOffset; //Se calcula la cordenada para dibujar el nodo hijo con el valor de x - la compesacion
                int childY = y + 2 * nodoRadio; // La línea debe comenzar desde la parte inferior del nodo padre
                drawLine(canvas, x, y + nodoRadio, childX, childY);
                drawTree(canvas, nodo.Izquierdo, childX, childY, xOffset / 2, depth + 1);
                //Codigo Anterior
                //drawLine(canvas, x - xOffset, y + nodeRadius, x, y + 2 * nodeRadius);
                //drawTree(canvas, node.Izquierdo, x - xOffset, y + 2 * nodeRadius, xOffset / 2, depth + 1);
            }
            if (nodo.Derecho != null)
            {
                int childX = x + xOffset;
                int childY = y + 2 * nodoRadio; // La línea debe comenzar desde la parte inferior del nodo padre
                drawLine(canvas, x, y + nodoRadio, childX, childY);
                drawTree(canvas, nodo.Derecho, childX, childY, xOffset / 2, depth + 1);
                //Codigo Anterior
                //drawLine(canvas, x + xOffset, y + nodeRadius, x, y + 2 * nodeRadius);
                //drawTree(canvas, node.Derecho, x + xOffset, y + 2 * nodeRadius, xOffset / 2, depth + 1);
            }
        }
        private void drawLine(Canvas canvas, int startX, int startY, int stopX, int stopY)
        {
            canvas.drawLine(startX, startY, stopX, stopY, linePaint);
        }
    }
}