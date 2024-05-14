package com.umg.abe_umg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    ArbolBinario ABE = new ArbolBinario();
    TextView lblResultado,txtPreOrden,txtInOrden,txtPosOrden;
    EditText txtIngreso;
    Button btnvalidar,btnDibujar;

    class Vista extends View
    {
        public Vista(Context context) {
            super(context);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtIngreso = (EditText) findViewById(R.id.txtIngreso);
        lblResultado = (TextView) findViewById(R.id.lblResultado);
        txtPreOrden = (TextView) findViewById(R.id.txtPreOrden);
        txtInOrden = (TextView) findViewById(R.id.txtInOrden);
        txtPosOrden = (TextView) findViewById(R.id.txtPosOrden);
        btnvalidar = (Button) findViewById(R.id.btnValidar);
        btnDibujar = (Button) findViewById(R.id.btnDibujar);

        btnvalidar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Cadena = txtIngreso.getText().toString();
                ABE = new ArbolBinario(Cadena);
                lblResultado.setText(""+ABE.EvaluaExpresion());

                //Llenado de los TextBox en el xml con los ordenes del arbol
                txtPreOrden.setText(String.format("Preorden: %s", ABE.toString(0)));
                txtInOrden.setText(String.format("Inorden: %s", ABE.toString(1)));
                txtPosOrden.setText(String.format("Postorden: %s", ABE.toString(2)));
            }
        });

        btnDibujar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Cadena = txtIngreso.getText().toString();

                //Traslado de informacion al segundo Activity
                Intent i = new Intent(v.getContext(), Arbol.class);
                i.putExtra("cadena",Cadena);
                startActivity(i);
            }
        });
    }
}