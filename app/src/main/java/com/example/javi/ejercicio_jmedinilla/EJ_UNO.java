package com.example.javi.ejercicio_jmedinilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EJ_UNO extends AppCompatActivity implements View.OnClickListener {

    Button boton_divisa;
    Button elegir_cambio;
    EditText texto_dolar;
    EditText texto_euro;
    RadioButton rd_dolar;
    RadioButton rd_euro;

    String cantidad;
    //Constantes que almacenan el valor de cambio de divisas
    static final double cambio_uno = 0.8908;
    static final double cambio_dos = 1.1224;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primero);

        //Botón que calcula el cambio de divisas
        boton_divisa = (Button)findViewById(R.id.btn_convertirDivisa);
        boton_divisa.setOnClickListener(this);
        //Botón que lleva a la vista que permite elegir un valor de cambio personalizado
        elegir_cambio = (Button)findViewById(R.id.btn_elegirCambio);
        elegir_cambio.setOnClickListener(this);
        texto_dolar = (EditText)findViewById(R.id.caja_dolar);
        texto_euro = (EditText)findViewById(R.id.caja_euro);
        rd_dolar = (RadioButton)findViewById(R.id.radio_dolar);
        rd_euro = (RadioButton)findViewById(R.id.radio_euro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_primero, menu);
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
        try {
            //Si el botón presionado es el de cambio de divisas, procede al cambio
            if (v == boton_divisa) {
                //Si está marcada la opción de convertir de dólares a euros,
                //obtiene el texto de la caja de texto de dólares y escribe
                //el cambio correspondiente en la casilla para los euros
                if (rd_dolar.isChecked()) {
                    cantidad = texto_dolar.getText().toString();
                    texto_euro.setText(Dolar_A_Euro(Double.parseDouble(cantidad)));
                }
                //En caso contrario, si es la opción de euros a dólares la
                //marcada, realiza el proceso contrario
                if (rd_euro.isChecked()) {
                    cantidad = texto_euro.getText().toString();
                    texto_dolar.setText(Euro_A_Dolar(Double.parseDouble(cantidad)));
                }
            }
            //Si se pulsa el botón de elegir un cambio de divisas personalizado
            //se va hacia otra actividad que te permite hacerlo
            if (v == elegir_cambio) {
                in = new Intent(this, ElegirCambio.class);
                startActivity(in);
            }
        }
        catch (Exception e) {
            //No hace nada en caso de excepción, tan solo evita el cierre de la aplicación
        }
    }

    //Convierte el valor obtenido de dólares a euros y lo devuelve formateado
    public String Dolar_A_Euro(Double d){
        double valor = d * cambio_uno;
        return String.format("%.2f", valor);
    }

    //Convierte el valor obtenido de euros a dólares y lo devuelve formateado
    public String Euro_A_Dolar(Double d){
        double valor = d * cambio_dos;
        return String.format("%.2f", valor);
    }
}
