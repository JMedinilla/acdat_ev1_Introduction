package com.example.javi.ejercicio_jmedinilla;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class EJ_DOS extends AppCompatActivity implements View.OnClickListener {

    EditText texto;
    Button boton;
    //Constante que define el cambio a realizar entre centímetros y pulgadas
    static final double cambio = 0.3937;
    double centimetros = 0;
    double pulgadas = 0;
    String cantidad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        boton = (Button)findViewById(R.id.btn_convertir);
        boton.setOnClickListener(this);
        texto = (EditText)findViewById(R.id.caja_cm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_segundo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == boton){
            try{
                //Recoge la cantidad de centímetros introducidos
                cantidad = texto.getText().toString();
                centimetros = Double.parseDouble(cantidad);
                //Calcula las pulgadas que equivalen a dicha cantidad
                pulgadas = centimetros * cambio;

                //Cambia el formato del texto para que sea visto solo con tres decimales
                DecimalFormat formateador = new DecimalFormat("###0.###");
                double num = pulgadas;
                num = formateador.parse(formateador.format(num)).doubleValue();

                //Muestra el resultado en una ventaja emergente
                AlertDialog.Builder popup = new AlertDialog.Builder(this);
                popup.setTitle("Conversión");
                popup.setMessage(centimetros + " centímetro(s) equivale(n) a " + num + " pulgada(s).");
                popup.setPositiveButton("OK", null);
                popup.show();
            }
            catch (Exception e){
                //No hace nada, evita e cierre forzoso de la aplicación
            }
        }
    }
}
