package com.umg.abe_umg;

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

    TextView lblResultado,txtOrden;
    EditText txtIngreso;
    Button btnvalidar;
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

        btnvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cadena = txtIngreso.getText().toString();
                ArbolBinario ABE = new ArbolBinario(Cadena);

                lblResultado.setText(""+ABE.EvaluaExpresion());
                txtOrden.setText(ABE.toString(1));
            }
        });

    }
}