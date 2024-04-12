package com.umg.abe_umg;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
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
                String Cadena = txtIngreso.getText().toString();
                ABE = new ArbolBinario(Cadena);
                lblResultado.setText(""+ABE.EvaluaExpresion());

                txtOrden.setText(ABE.toString(1));
            }
        });

        btnDibujar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cadena = txtIngreso.getText().toString();

                //Traslado de informacion al segundo Activity
                Intent i = new Intent(v.getContext(), Arbol.class);
                i.putExtra("cadena",Cadena);
                startActivity(i);
            }
        });
    }
}