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

        public Vista(Context context) {
            super(context);
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
                Context context = getApplicationContext();
                Canvas canvas = new Canvas();
                Lienzo miLienzo = new Lienzo(context);
                String Cadena = txtIngreso.getText().toString();
                ABE = new ArbolBinario(Cadena);

                lblResultado.setText(""+ABE.EvaluaExpresion());
                //pintar(canvas,30,60,ABE.Raiz);
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