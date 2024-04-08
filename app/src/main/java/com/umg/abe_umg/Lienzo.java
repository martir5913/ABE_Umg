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
    boolean flag= true;
    public Lienzo(Context context) {
        super(context);
    }
    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private ArbolBinario Arbol;
    public static final int DIAMETRO = 200;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 230;


    public void setArbol(ArbolBinario arbol)
    {
        this.Arbol = arbol;

        //repaint();
    }

    @Override
    public void onDraw(Canvas canvas){

        if(flag) {
            super.onDraw(canvas);
            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();
            Paint paint = new Paint();
            ABE = new ArbolBinario("1+1");
            flag = false;
        }
        setArbol(ABE);
//        paint.setStrokeWidth(10);
//        paint.setARGB(255,255,0,0);
//        canvas.drawLine(100,100,600,800,paint);
//        paint.setARGB(255,255,128,0);
//        canvas.drawCircle(600,600,500,paint);
        pintar(canvas,30,60,Arbol.Raiz);
    }
    public void pintar(Canvas g, int x,int y, NodoArbol subArbol)
    {
        Paint.FontMetrics fm = new Paint.FontMetrics();
        Paint mTxtPaint = new Paint();
        Paint paint = new Paint();
        if (subArbol!=null)
        {
            int EXTRA = Arbol.NodosCompletos(subArbol)*ANCHO/2;
            paint.setARGB(255,0,0,0);
            mTxtPaint.setColor(Color.RED);
            mTxtPaint.setTextSize(50);
            mTxtPaint.getFontMetrics(fm);
            //g.drawOval(x+800, y-400, DIAMETRO, ANCHO,paint);
            g.drawText(subArbol.Dato.toString(), x+400+fm.top, y+800+fm.bottom,mTxtPaint);


            //https://azzits.wordpress.com/tag/android-drawtext-with-background-color/

            if (subArbol.Izquierdo != null)
            {
                g.drawLine(x+350, y +800, x+650 - ANCHO - EXTRA, y+700 + ANCHO,paint);
            }
            if (subArbol.Derecho != null)
            {
                g.drawLine(x+355 , y +800, x+55  + ANCHO + EXTRA, y+700 + ANCHO,paint);
            }
            pintar(g, x+400 - ANCHO - EXTRA, y-80 + ANCHO, subArbol.Izquierdo);
            pintar(g, x-400 + ANCHO + EXTRA, y-80 + ANCHO, subArbol.Derecho);
        }
    }


}
