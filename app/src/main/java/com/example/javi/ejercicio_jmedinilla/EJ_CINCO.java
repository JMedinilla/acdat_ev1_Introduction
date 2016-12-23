package com.example.javi.ejercicio_jmedinilla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EJ_CINCO extends AppCompatActivity implements OnClickListener {

    Button suma;
    Button limpio;
    EditText ini;
    EditText fin;
    TextView res;

    int inicial;
    int ultimo;
    double resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quinto);

        suma = (Button)findViewById(R.id.btn_Sumar);
        suma.setOnClickListener(this);
        limpio = (Button)findViewById(R.id.btn_Limpiar);
        limpio.setOnClickListener(this);
        ini = (EditText)findViewById(R.id.tbx_inicio);
        fin = (EditText)findViewById(R.id.tbx_fin);
        res = (TextView)findViewById(R.id.lbl_Resultado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quinto, menu);
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
            //Si se pulsa el botón de limpiar, los campos de la interfaz gráfica se reinician
            if (v == limpio) {
                ini.setText("");
                fin.setText("");
                res.setText("");
            }
            //Si por el contrario se pulsa el de sumar, se reinicia resultado y se calcula de nuevo
            if (v == suma) {
                resultado = 0;

                //Si falta alguna casilla por tener un número escrito, se avisa al usuario
                if (ini.getText().toString().equals("") || fin.getText().toString().equals("")) {
                    res.setText("Falta algún número");
                } else {
                    inicial = Integer.parseInt(ini.getText().toString());
                    ultimo = Integer.parseInt(fin.getText().toString());

                    //También se avisará e impedirá el cálculo cuando el número inicial sea
                    //mayor que el otro
                    if (inicial > ultimo) {
                        res.setText("El número inicial es mayor");
                    } else {
                        //Si ambos cambos son correctos, un bucle recorrerá y sumará cada número
                        //comprendido entre ellos paraa luego mostrarlo en la etiqueta de resultados
                        for (double i = inicial; i <= ultimo; i++) {
                            resultado += i;
                        }
                        res.setText(String.format("%.0f", resultado));
                    }
                }
            }
        }
        catch (Exception e) {
            //Evita el cierre forzoso de la aplicación
        }
    }
}
