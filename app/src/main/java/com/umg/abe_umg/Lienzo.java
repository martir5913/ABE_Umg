package com.umg.abe_umg;

import android.content.AttributionSource;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Lienzo  extends View {
    ArbolBinario ABE;
    public Lienzo(Context context) {
        super(context);
    }
    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private ArbolBinario Arbol;

    public static final int DIAMETRO = 400;
    public static final int RADIO = DIAMETRO / 10;
    public static final int ANCHO = 400;

    public void setArbol(ArbolBinario arbol)
    {
        this.Arbol = arbol;

        //repaint();
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        ABE = new ArbolBinario("3+3");
        setArbol(ABE);
//        paint.setStrokeWidth(10);
//        paint.setARGB(255,255,0,0);
//        canvas.drawLine(100,100,600,800,paint);
//        paint.setARGB(255,255,128,0);
//        canvas.drawCircle(600,600,500,paint);
        pintar(canvas,getWidth()/2,100,Arbol.Raiz);
    }
    public void pintar(Canvas g, int x,int y, NodoArbol subArbol)
    {

        Paint mTxtPaint = new Paint();
        Paint paint = new Paint();
        if (subArbol!=null)
        {
            int EXTRA = Arbol.NodosCompletos(subArbol)*ANCHO/2;
            paint.setARGB(255,0,0,0);
            //mTxtPaint.setColor(Color.P);
            mTxtPaint.setColor(Color.GREEN);
            mTxtPaint.setTextSize(20);
            g.drawText(subArbol.Dato.toString(), x+420, y+480,mTxtPaint);
            //g.drawOval(x, y, DIAMETRO, ANCHO,paint);

            //https://azzits.wordpress.com/tag/android-drawtext-with-background-color/

            if (subArbol.Izquierdo != null)
            {
                g.drawLine(x, y + RADIO, x + RADIO - ANCHO - EXTRA, y + ANCHO,paint);
            }
            if (subArbol.Derecho != null)
            {
                g.drawLine(x + DIAMETRO, y + RADIO, x + RADIO + ANCHO + EXTRA, y + ANCHO,paint);
            }
            pintar(g, x - ANCHO - EXTRA, y + ANCHO, subArbol.Izquierdo);
            pintar(g, x + ANCHO + EXTRA, y + ANCHO, subArbol.Derecho);
        }
    }


}
