package com.example.javi.ejercicio_jmedinilla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class EJ_TRES extends AppCompatActivity implements View.OnClickListener {

    Button btn_web;
    EditText edt_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercero);

        btn_web = (Button)findViewById(R.id.btn_buscarWEB);
        btn_web.setOnClickListener(this);
        edt_web = (EditText)findViewById(R.id.edt_direccionWEB);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tercero, menu);
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
            //Se recoge la cadena introducida en la caja de texto
            //y se llama al método encargado de abrir dicha URL
            abrirWeb(edt_web.getText().toString());
        }
        catch (Exception e) {
            //Evita el cierre de la aplicación en caso de excepción
        }
    }

    public void abrirWeb(String dir) {
        //Crea un objeto de la clase WebVisor, definido según
        //la dirección indicada, y abre tal URL en la aplicación
        WebView visor = new WebView(this);
        setContentView(visor);
        visor.loadUrl(dir);

        //EL CODIGO A CONTINUACIÓN ES PARA ABRIR EL NAVEGADOR
        //NO ES LO QUE PIDE EL EJERCICIO
        //
        //Uri pagWeb = Uri.parse(dir);
        //Intent in = new Intent(Intent.ACTION_VIEW, pagWeb);
        //if (in.resolveActivity(getPackageManager()) != null) {
        //startActivity(in);
        //}
        //
    }
}
