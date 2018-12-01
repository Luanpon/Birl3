package com.example.luanp.birl;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class ExeActionActivity extends AppCompatActivity implements SensorEventListener{

    private int eixo;
    private int erro;
    private int series;
    private int reps;
    private int segs;
    private String exercicio;

    private TextView exerc;
    private TextView serie;
    private TextView repsdesc;
    private TextView num_reps;

    private int series2;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int passo;
    private float minpos;
    private float maxpos;
    private float milhao;
    private double contador;
    private Vibrator vibrator;
    private CountDownTimer timer1;
    private CountDownTimer timer2;
    private CountDownTimer timer3;
    private int passo2;
    private int passo3;
    private int passo4;
    long milisegundos;

    private ImageButton bt_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exe_action);

        eixo = getIntent().getIntExtra("eixo",0);
        erro = getIntent().getIntExtra("erro",0);
        series =   getIntent().getIntExtra("series",0);
        reps = getIntent().getIntExtra("reps",0);
        segs = getIntent().getIntExtra("segs",0);
        exercicio = getIntent().getStringExtra("exercicio");

        exerc = (TextView) findViewById(R.id.textView10);
        serie = (TextView) findViewById(R.id.textView15);
        repsdesc = (TextView) findViewById(R.id.textView16);
        num_reps = (TextView) findViewById(R.id.textView17);

        bt_home = (ImageButton) findViewById(R.id.bt_home);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        series2=1;
        passo2 = 0;
        passo3 = 0;
        passo4 = 0;

        exerc.setText(exercicio);

        timer1 = new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {

                num_reps.setText("" + (millisUntilFinished) / 1000);
            }

            public void onFinish() {
                passo2 = 1;
                passo3 = 0;
                num_reps.setText("0");
                vibrator.vibrate(300);
                timer1.cancel();
            }
        };

        timer2 = new CountDownTimer((segs*1000)-3000, 1000) {

            public void onTick(long millisUntilFinished) {

                milisegundos = millisUntilFinished+3000;
                num_reps.setText("" + (milisegundos) / 1000);
            }

            public void onFinish() {
                //milisegundos -= 1000;
                //num_reps.setText("" + (milisegundos) / 1000);
                passo2 = 0;
                passo3 = 0;
                series2 = series2+1;
                timer2.cancel();
            }
        };

        timer3 = new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {

                num_reps.setText("" + (millisUntilFinished) / 1000);
            }

            public void onFinish() {
                serie.setText("Exercicio Concluído");
                repsdesc.setText("Fim do Treinamento");
                num_reps.setText("0");
                vibrator.vibrate(800);
                timer3.cancel();
            }
        };

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(ExeActionActivity.this,mSensor,SensorManager.SENSOR_DELAY_GAME);

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volta_menu();
            }
        });


    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if(series2<=series) {

            if (passo2 == 0 && passo3 == 0) {

                passo3 = 1;
                contador = 0;
                serie.setText("Coloque-se na Posição Inicial do Exercício");
                repsdesc.setText("Descanso");
                timer1.start();

            }

            if (passo2 == 1) {

                serie.setText(series2 + "ª Série");
                repsdesc.setText("Repetições");

                if (event.values[1] < minpos) {
                    minpos = event.values[1];
                }

                if (event.values[1] > maxpos) {
                    maxpos = event.values[1];
                }

                if (maxpos - minpos >= erro && maxpos != (milhao * (-1)) && minpos != milhao && contador < reps) {

                    contador = contador + (0.5);
                    minpos = milhao;
                    maxpos = milhao * (-1);
                    num_reps.setText(Integer.toString((int) contador));

                }

                if (contador >= reps) {
                    num_reps.setText(Integer.toString((int) contador));
                    passo2 = 2;
                    passo3 = 0;
                }

            }

            if (passo2 == 2 && passo3 == 0) {

                passo3 = 1;
                vibrator.vibrate(300);
                serie.setText(series2 + "ª Série");
                repsdesc.setText("Descanso");
                timer2.start();

            }

        }
        else {

            if(passo4==0){
                passo4 = 1;
                timer3.start();
            }

        }

        //Log.d("Exercicio:", " X=" + event.values[0] + " | Y=" + event.values[1] + " | Z=" + event.values[2]);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void decrementa(int valor){

        num_reps.setText(valor);

    }

    public void volta_menu(){

        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
        finish();

    }

}
