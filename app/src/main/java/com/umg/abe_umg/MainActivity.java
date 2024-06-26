package com.umg.abe_umg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void onf()
    {
        String Cadena = txtIngreso.getText().toString();
        ABE = new ArbolBinario(Cadena);

        // Verificar si la entrada contiene caracteres no permitidos
        boolean entradaValida = true;
        for (char c : Cadena.toCharArray())
        {
            if (!Character.isDigit(c) && !esOperadorMatematico(c) && !esParentesis(c))
            {
                entradaValida = false;
                break;
            }
        }

        if (entradaValida)
        {
            // Se valida la cadena si comienza con alguno de los operadores
            Pattern pattern = Pattern.compile("^[\\+\\-\\*\\/^].*");
            Matcher matcher = pattern.matcher(Cadena);

            // Se verifica si cumple o no
            if (matcher.find())
            {
                lblResultado.setText("Aun no se admiten negativos");
            }
            else
            {
                // Validar si la expresion contiene el caracter no divicible
                if (Cadena.contains("/0"))
                {
                    lblResultado.setText("No se puede dividir ente 0");
                }
                else
                {
                    lblResultado.setText("" + ABE.EvaluaExpresion());
                }
            }
        }
        else
        {
            lblResultado.setText("Expresion sin poder calcular");
        }
        //Llenado de los TextBox en el xml con los ordenes del arbol
        txtPreOrden.setText(String.format("PREORDEN: %s", ABE.toString(0)));
        txtInOrden.setText(String.format("INORDEN: %s", ABE.toString(1)));
        txtPosOrden.setText(String.format("POSTORDEN: %s", ABE.toString(2)));
    }
    // Metodo para verificar si un caracter es un operador matematico
    private boolean esOperadorMatematico(char c)
    {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
    // Metodo para verificar si un caracter es un parentesis
    private boolean esParentesis(char c)
    {
        return c == '(' || c == ')';
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

        EditText editText = findViewById(R.id.txtIngreso);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Aquí ejecutas tu procedimiento
                    onf();
                    //Para ocultar el teclado Virtual
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        btnvalidar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onf();
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