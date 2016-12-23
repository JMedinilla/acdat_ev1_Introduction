package com.example.javi.ejercicio_jmedinilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Controles que referencian a los botones del menú
    Button boton_uno;
    Button boton_dos;
    Button boton_tres;
    Button boton_cuatro;
    Button boton_cinco;
    Button boton_seis;
    //Objeto de tipo Intent para ir a cualquiera de los distintos ejercicios
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ejercicio 1
        boton_uno = (Button)findViewById(R.id.btnEjercicio1);
        boton_uno.setOnClickListener(this);
        //Ejercicio 2
        boton_dos = (Button)findViewById(R.id.btnEjercicio2);
        boton_dos.setOnClickListener(this);
        //Ejercicio 3
        boton_tres = (Button)findViewById(R.id.btnEjercicio3);
        boton_tres.setOnClickListener(this);
        //Ejercicio 4
        boton_cuatro = (Button)findViewById(R.id.btnEjercicio4);
        boton_cuatro.setOnClickListener(this);
        //Ejercicio 5
        boton_cinco = (Button)findViewById(R.id.btnEjercicio5);
        boton_cinco.setOnClickListener(this);
        //Ejercicio 6
        boton_seis = (Button)findViewById(R.id.btnEjercicio6);
        boton_seis.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if(v == boton_uno) {
            //Ir al ejercicio 1
            in = new Intent(this, EJ_UNO.class);
            startActivity(in);
        }
        if(v == boton_dos) {
            //Ir al ejercicio 2
            in = new Intent(this, EJ_DOS.class);
            startActivity(in);
        }
        if(v == boton_tres) {
            //Ir al ejercicio 3
            in = new Intent(this, EJ_TRES.class);
            startActivity(in);
        }
        if(v == boton_cuatro) {
            //Ir al ejercicio 4
            in = new Intent(this, EJ_CUATRO.class);
            startActivity(in);
        }
        if(v == boton_cinco) {
            //Ir al ejercicio 5
            in = new Intent(this, EJ_CINCO.class);
            startActivity(in);
        }
        if(v == boton_seis) {
            //Ir al ejercicio 6
            in = new Intent(this, CRAPS.class);
            startActivity(in);
        }
    }
}
