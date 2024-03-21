package com.umg.abe_umg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ArbolBinario ABE = new ArbolBinario();
    TextView lblResultado,txtOrden;
    EditText txtIngreso;
    Button btnvalidar,btnDibujar;

    class Vista extends View {

        private ArbolBinario Arbol;

        public static final int DIAMETRO = 30;
        public static final int RADIO = DIAMETRO / 2;
        public static final int ANCHO = 30;

        public Vista(Context context) {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStrokeWidth(10);
            paint.setARGB(255, 255, 0, 0);
            canvas.drawLine(100, 100, 600, 800, paint);
//        paint.setARGB(255,255,128,0);
//        canvas.drawCircle(600,600,500,paint);
            pintar(canvas,getWidth()/2,20,Arbol.Raiz);
        }
        public void setArbol(ArbolBinario arbol)
        {
            this.Arbol = arbol;
            //paint();
        }
        public void pintar(Canvas g, int x,int y, NodoArbol subArbol)
        {
            Paint paint = new Paint();
            if (subArbol != null) {
                int EXTRA = Arbol.NodosCompletos(subArbol) * ANCHO / 2;

                g.drawOval(x, y, DIAMETRO, ANCHO, paint);
                g.drawText(subArbol.Dato.toString(), x + 12, y + 18, paint);

                if (subArbol.Izquierdo != null) {
                    g.drawLine(x, y + RADIO, x + RADIO - ANCHO - EXTRA, y + ANCHO, paint);
                }
                if (subArbol.Derecho != null) {
                    g.drawLine(x + DIAMETRO, y + RADIO, x + RADIO + ANCHO + EXTRA, y + ANCHO, paint);
                }
                pintar(g, x - ANCHO - EXTRA, y + ANCHO, subArbol.Izquierdo);
                pintar(g, x + ANCHO + EXTRA, y + ANCHO, subArbol.Derecho);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtIngreso = (EditText) findViewById(R.id.txtIngreso);
        lblResultado = (TextView) findViewById(R.id.lblResultado);
        txtOrden = (TextView) findViewById(R.id.txtOrden);
        btnvalidar = (Button) findViewById(R.id.btnValidar);
        btnDibujar = (Button) findViewById(R.id.btnDibujar);

        btnvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cadena = txtIngreso.getText().toString();
                ABE = new ArbolBinario(Cadena);

                lblResultado.setText(""+ABE.EvaluaExpresion());
                txtOrden.setText(ABE.toString(1));
            }
        });

        btnDibujar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Lienzo miLienzo = new Lienzo(context);
                miLienzo.setArbol(ABE);
            }
        });
    }
}