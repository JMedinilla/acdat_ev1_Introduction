package com.example.javi.ejercicio_jmedinilla;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class EJ_CUATRO extends AppCompatActivity implements OnClickListener {

    AlertDialog.Builder popup;

    Contador temp;
    Button btnComenzar;
    Button btnSuma;
    Button btnResta;
    TextView lblTiempo;
    TextView lblCafeses;
    TextView lblMssg;
    MediaPlayer sonido;
    Switch sentidoo;
    long tiempoActual;

    protected static final String ESTADO_PAUSA = "tiempo de la pausa";
    protected static final String ESTADO_CAFES = "numero de cafes";
    protected static final String ESTADO_SWITCH = "contador Up/Down";
    protected static final String ESTADO_CUENTA = "contando";
    protected static final String ESTADO_TIEMPO = "tiempo actual del contador";

    int minutos;
    int segundos;
    int cafeses = 0;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Salvar las variables al cambiar la orientaciÃ³n
        savedInstanceState.putInt(ESTADO_PAUSA, minutos);
        savedInstanceState.putInt(ESTADO_CAFES, cafeses);
        savedInstanceState.putBoolean(ESTADO_SWITCH, sentidoo.isChecked());
        Boolean contando = !btnComenzar.isEnabled();
        savedInstanceState.putBoolean(ESTADO_CUENTA, contando);
        if (contando)
            savedInstanceState.putLong(ESTADO_TIEMPO, tiempoActual);

        // Siempre se debe llamar al constructor de la clase superior
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        try {
            int pausa, cafes;
            boolean down, contando;
            long tiempo;
            // Siempre se debe llamar al constructor de la clase superior
            super.onRestoreInstanceState(savedInstanceState);

            // Restaurar las variables al cambiar la orientaciÃ³n
            pausa = savedInstanceState.getInt(ESTADO_PAUSA);
            cafes = savedInstanceState.getInt(ESTADO_CAFES);
            down = savedInstanceState.getBoolean(ESTADO_SWITCH);
            contando = savedInstanceState.getBoolean(ESTADO_CUENTA);
            tiempo = savedInstanceState.getLong(ESTADO_TIEMPO);

            cafeses = cafes;
            minutos = pausa;
            lblCafeses.setText(String.valueOf(cafes));
            sentidoo.setChecked(down);
            if (contando) {

                temp = new Contador(minutos * 60 * 1000, 10, minutos * 60 * 1000 - tiempo);
                temp.start();

                btnResta.setEnabled(false);
                btnSuma.setEnabled(false);
                btnComenzar.setEnabled(false);
            }
            else
                lblTiempo.setText("" + minutos + ":00");
        }
        catch(Exception e) {
            //
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);

        btnComenzar = (Button)findViewById(R.id.btn_comenzar);
        btnComenzar.setOnClickListener(this);
        btnSuma = (Button)findViewById(R.id.btn_Suma);
        btnSuma.setOnClickListener(this);
        btnResta = (Button)findViewById(R.id.btn_Resta);
        btnResta.setOnClickListener(this);
        lblTiempo = (TextView)findViewById(R.id.lbl_Tiempo);
        lblCafeses = (TextView)findViewById(R.id.lbl_cafeses);
        lblMssg = (TextView)findViewById(R.id.lbl_Mssg);
        popup = new AlertDialog.Builder(this);
        sonido = MediaPlayer.create(this, R.raw.snd);
        sentidoo = (Switch)findViewById(R.id.sentido);

        minutos = 0;
        segundos = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cuarto, menu);
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
        btnComenzar.setText("Comenzar");
        lblMssg.setText("--");
        try {
            //Si se pusa el botón de comenzar tras llegar a los 10 cafés, se inicia el contador
            if (cafeses == 10) {
                cafeses = 0;
                lblCafeses.setText("" + cafeses);
            }
            else {
                //Si se pulsa el botón de comenzar, se imposibilita el poder pulsar ningún
                //botón y se inicia la cuenta atrás del contador
                if (v == btnComenzar) {
                    btnSuma.setEnabled(false);
                    btnResta.setEnabled(false);
                    btnComenzar.setEnabled(false);
                    temp = new Contador(minutos * 60 * 1000, 10);
                    temp.start();
                }
                //Si se pulsa el botón de suma, se añade un minuto al contador
                if (v == btnSuma) {
                    minutos++;
                    lblTiempo.setText(String.format("%02d", minutos) + ":" + String.format("%02d", segundos));
                }
                //Si se pulsa el botón de resta se quita un minuto al contador
                if (v == btnResta & minutos > 0) {
                    minutos--;
                    lblTiempo.setText(String.format("%02d", minutos) + ":" + String.format("%02d", segundos));
                }
            }
        }
        catch (Exception e) {
            //No hace nada, tan solo evita el cierre de la aplicación
        }
    }

    //Clase que implementa en contador en el ejercicio
    public class Contador extends CountDownTimer {

        long total, intervalo;
        boolean ascendente;

        public Contador(long millisUntilFinished, long countDownInterval) {
            super(millisUntilFinished, countDownInterval);

            //total = millisUntilFinished;
            total = minutos * 1000 * 60;
        }

        public Contador(long millisUntilFinished, long countDownInterval, long anterior) {
            super(millisUntilFinished, countDownInterval);

            total = millisUntilFinished + anterior;
        }

        @Override
        public void onTick(long millisUntilFinished) {

            ascendente = sentidoo.isChecked();
            tiempoActual = millisUntilFinished;

            if (ascendente) {
                intervalo = total - millisUntilFinished;
            }
            else {
                intervalo = millisUntilFinished;
            }

            //En cada toque de reloj, se calculan los minutos y segundos
            //restantes para llegar a 0 y se escribe cada segundo en la
            //etiqueta que muestra el reloj
            long mins = (intervalo/1000)/60;
            long secs = (intervalo/1000)%60;
            lblTiempo.setText(String.format("%02d", mins) + ":" + String.format("%02d", secs));
        }

        @Override
        public void onFinish() {
            //Al terminar el contador se avisa de ello encima de este mismo
            lblMssg.setText("¡Pausa terminada!");
            //En consecuencia, los botones vuelven a estar preparados para otra cuenta
            btnSuma.setEnabled(true);
            btnResta.setEnabled(true);
            btnComenzar.setEnabled(true);
            //Se reinicia el contador a 0 y se ejecuta el sonido que indica el fin de la pausa
            lblTiempo.setText("00:00");
            minutos = 0;
            sonido.start();
            //Tras esto, se incrementa el valor de contador de cafés y se muestra en la pantalla
            cafeses++;
            lblCafeses.setText("" + cafeses);
            if (cafeses == 10) {
                //En caso de que esta cantidad sea igual a 10, se cambia el texto del
                //botón que da comienzo al contados y se avisa mediante un mensaje
                //emergente de que se ha alcanzado el máximo de cafés del día
                btnComenzar.setText("Reiniciar");
                lblMssg.setText("¡Máximo 10 cafés!");
                popup.setMessage("¡Reinicia el contador!\n¡Solo 10 cafés por día!");
                popup.setPositiveButton("OK", null);
                popup.show();
            }
        }
    }
}
