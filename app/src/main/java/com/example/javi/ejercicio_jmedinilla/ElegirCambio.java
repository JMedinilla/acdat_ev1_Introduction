package com.example.javi.ejercicio_jmedinilla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ElegirCambio extends AppCompatActivity implements View.OnClickListener {

    Button btnC;
    EditText edtI;
    EditText edtC;
    EditText edtR;

    String camb = "";
    String cant = "";
    double var1 = 0;
    double var2 = 0;
    double var3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_cambio);

        btnC = (Button)findViewById(R.id.btn_converCambio);
        btnC.setOnClickListener(this);
        edtI = (EditText)findViewById(R.id.edt_inicial);
        edtC = (EditText)findViewById(R.id.edt_cambio);
        edtR = (EditText)findViewById(R.id.edt_resultado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_elegir_cambio, menu);
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
            //Recoge el texto que identifica la cantidad de la moneda introducida
            //y el cambio de divisas introducido por el usuario y al realizar el
            //cambio, se muestra en una caja de texto
            camb = edtC.getText().toString();
            cant = edtI.getText().toString();
            var1 = Double.parseDouble(camb);
            var2 = Double.parseDouble(cant);
            var3 = var1 * var2;
            edtR.setText(String.format("%.2f", var3));
        }
        catch (Exception e) {
            //No hace nada en caso de excepción, tan solo evita el cierre de la aplicación
        }
    }
}
