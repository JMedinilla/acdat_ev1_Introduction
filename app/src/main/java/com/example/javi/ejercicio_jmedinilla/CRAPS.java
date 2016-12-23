package com.example.javi.ejercicio_jmedinilla;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

/*
Este ejercicio implementa el juego de dados CRAPS, visto en la película Titanic,
forma en la que Leonardo DiCaprio gana sus pases para el barco. Las reglas son:

El jugador tira dos dados. En cada tirada se calcula la suma de os puntos obtenidos
en ambos. Si este número resulta ser el 7 o el 11 en la primera tirada, el jugador
gana de forma automática, mientras que si la suma da como resultado 2, 3 o 12, es
una derrota tambien automática.

Para ganar en condiciones normales, el objetivo consiste en que el jugador continúe
tirando el dado hasta que obtenga la misma cifra que consiguió en la primera tirada,
que no fuera uno de los que proporciona una derrota o victoria automática. Si esto
sucede el jugador gana. Sin embargo, si antes de llegar de nuevo a su primera cifra
el jugador obtiene una suma de 7, pierde la partida.
 */


public class CRAPS extends AppCompatActivity implements View.OnClickListener {

    Button btnTirar;
    TextView numeroDadoUno;
    TextView numeroDadoDos;
    TextView numeroDadoSuma;
    TextView numeroVictorias;
    TextView numeroDerrotas;
    TextView numeroPrimeraTirada;

    Random dadoUno; //Primer dado
    Random dadoDos; //Segundo dado
    Random rnd; //Número aleatorio que ayudará a los dados a ser más aleatorios

    ArrayList<Integer> ganarAuto;
    ArrayList<Integer> perderAuto;

    int punto1; //Almacena el valor de la primera tirada
    int punto2; //Almacena el valor de las tiradas siguientes
    int vics; //Almacena la cantidad de victorias obtenidas
    int ders; //Almacena la cantidad de derrotas obtenidas
    boolean prmTrd; //Variable que indica si la actual es la primera tirada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craps);

        btnTirar = (Button)findViewById(R.id.btn_dados);
        btnTirar.setOnClickListener(this);
        numeroDadoUno = (TextView)findViewById(R.id.lbl_numero_uno);
        numeroDadoDos = (TextView)findViewById(R.id.lbl_numero_dos);
        numeroDadoSuma = (TextView)findViewById(R.id.lbl_numero_resultado);
        numeroVictorias = (TextView)findViewById(R.id.lbl_numero_victorias);
        numeroDerrotas = (TextView)findViewById(R.id.lbl_numero_derrotas);
        numeroPrimeraTirada = (TextView)findViewById(R.id.lbl_numero_primera);

        //Pone las victorias y derrotas a 0, reinicia las variables que contienen
        //las sumas obtenidas, se rellenan las listas que contienen los valores de
        //derrota o victoria automática y se llama al método que reinicia los controles
        //para la siguiente tirada.
        vics = 0;
        ders = 0;
        punto1 = -1;
        punto2 = -1;

        ganarAuto = new ArrayList<>();
        perderAuto = new ArrayList<>();
        ganarAuto.add(7); //Primera condición de victoria automática
        ganarAuto.add(11); //Segunda condición de victoria automática
        perderAuto.add(2); //Primera condición de derrota automática
        perderAuto.add(3); //Segunda condición de derrota automática
        perderAuto.add(12); //Tercera condición de derrota automática

        Inicializar();
        rnd = new Random();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_craps, menu);
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

        //Se inician las variables que obtendrán la
        // tirada de dado y se prepara el mensaje emergente
        int valorDado1;
        int valorDado2;
        AlertDialog.Builder popup = new AlertDialog.Builder(this);
        popup.setPositiveButton("OK", null);

        //Se inician los dados mediante una semilla aleatoria,
        //para que sume más aleatoriedad a las tiradas siguientes.
        dadoUno = new Random(rnd.nextInt());
        dadoDos = new Random(rnd.nextInt());

        //Mediante esta variable vemos si es la primera tirada o no, y pasamos
        //a una zona de código u otra según sea necesario.
        if (prmTrd) {
            //Lo primero es cambiar el valor de la variable para no volver a
            //pasar por aquí, y tras ello, tiramos los dados y guardamos los
            //números obtenidos en variables para operar con ellos de forma
            //más sencilla
            prmTrd = false;
            valorDado1 = dadoUno.nextInt(7);
            valorDado2 = dadoDos.nextInt(7);

            //Debido a que el método 'nextInt' de Java no es igual
            //que el método 'Next' de C#, no podemos indicar un
            //número mínimo para el objeto de tipo Random, así que
            //preguntaremos si el valor es 0 y sumaremos 1 en ese caso.
            if (valorDado1 == 0) {
                valorDado1 = 1;
            }
            if (valorDado2 == 0) {
                valorDado2 = 1;
            }

            //A continuación, escribimos estos valores en las etiquetas de texto
            //que les corresponden, lo sumaremos, y lo pondremos en el resultado
            //de la tirada, y en la etiqueta que nos dirá cual ha sido la primera
            //tirada de este turno.
            numeroDadoUno.setText("" + valorDado1);
            numeroDadoDos.setText("" + valorDado2);
            punto1 = valorDado1 + valorDado2;
            numeroDadoSuma.setText("" + punto1);
            numeroPrimeraTirada.setText("" + punto1);

            //Ya que hay condiciones automáticas de victoria o derrota, preguntamos.
            if (perderAuto.contains(punto1)) {
                popup.setTitle("¡Derrota!");
                popup.setMessage("¡Ha salido " + punto1 + "!\n\nSacar 2, 3 o 12 en la primera tirada es derrota automática");
                popup.show();
                //Después de poner el mensaje de derrota, aumentamos el contador y reiniciamos el juego.
                ders++;
                Inicializar();
            }
            else {
                //En caso de que el número obtenido no sea 2, 3 o 12, preguntamos por el 7 y el 11.
                if (ganarAuto.contains(punto1)) {
                    popup.setTitle("¡Victoria!");
                    popup.setMessage("¡Ha salido " + punto1 + "!\n\nSacar 7 u 11 en la primera tirada es victoria automática\n¡Enhorabuena!");
                    popup.show();
                    //Después de poner el mensaje de victoria, aumentamos el contador y reiniciamos el juego.
                    vics++;
                    Inicializar();
                }
            }
        }
        //En caso de que el juego no se haya resuelto en la primera tirada
        //pasaremos a esta zona de código, donde se harán exactamente los
        //mismos pasos, pero no escribiremos el número en la etiqueta que
        //indica la primera tirada, tan solo en el resultado de la suma de
        //ambos dados.
        else {
            valorDado1 = dadoUno.nextInt(7);
            valorDado2 = dadoDos.nextInt(7);

            if (valorDado1 == 0) {
                valorDado1 = 1;
            }
            if (valorDado2 == 0) {
                valorDado2 = 1;
            }

            numeroDadoUno.setText("" + valorDado1);
            numeroDadoDos.setText("" + valorDado2);
            punto2 = valorDado1 + valorDado2;
            numeroDadoSuma.setText("" + punto2);

            if (punto2 == 7) {
                //Esta vez preguntaremos tan solo por el número 7. Aunque sería mejor
                //poner este número en una constante.
                popup.setTitle("¡Derrota!");
                popup.setMessage("¡Ha salido " + punto2 + "!\n\nSacar un 7 tras la primera tirada antes de llegar al número obtenido en primer lugar es una derrota");
                popup.show();
                //Después de poner el mensaje de derrota, aumentamos el contador y reiniciamos el juego.
                ders++;
                Inicializar();
            }
            //Y finalmente, según las normas, si en las siguientes tiradas a la primera
            //no sale el 7, habrá que tirar hasta que la comparación siguiente sea
            //verdadera, y se vuelva a obtener el mismo número otra vez.
            else {
                if (punto2 == punto1) {
                    popup.setTitle("¡Victoria!");
                    popup.setMessage("¡Ha salido " + punto2 + "!\n\n¡Enhorabuena, has obtenido el número de nuevo!");
                    popup.show();
                    //Después de poner el mensaje de victoria, aumentamos el contador y reiniciamos el juego.
                    vics++;
                    Inicializar();
                }
            }
        }
    }

    //Reinicia las variables que contienen los puntos obtenidos en las tiradas y se
    //escribe en las cajas de texto un valor que indique que aún no se ha tirado.
    //También se cambia el valor del booleano que indica la primera tirada y se
    //actualiza la interfaz con las victorias y derrotas que hayan tenido lugar.
    public void Inicializar() {
        punto1 = -1;
        punto2 = -1;
        numeroDadoUno.setText("-");
        numeroDadoDos.setText("-");
        prmTrd = true;
        numeroPrimeraTirada.setText("0");
        numeroDadoSuma.setText("0");
        numeroVictorias.setText("" + vics);
        numeroDerrotas.setText("" + ders);
    }
}
