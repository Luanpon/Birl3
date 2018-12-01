package com.example.luanp.birl;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ExeRepsActivity extends AppCompatActivity {

    private ImageButton bt_menos;
    private ImageButton bt_mais;
    private ImageButton bt_avancar;
    private ImageButton bt_voltar;
    private TextView num_reps;
    private int reps = 1;
    private Handler mHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exe_reps);

        bt_menos = (ImageButton) findViewById(R.id.imageButton3);
        bt_mais = (ImageButton) findViewById(R.id.imageButton6);
        bt_avancar = (ImageButton) findViewById(R.id.imageButton16);
        bt_voltar = (ImageButton) findViewById(R.id.imageButton15);
        num_reps = (TextView) findViewById(R.id.textView12);

        num_reps.setText("0"+Integer.toString(reps));

        bt_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reps=reps-1;

                if(reps<1){
                    reps=1;
                }

                if(reps>9){
                    num_reps.setText(Integer.toString(reps));
                }

                if(reps<10){
                    num_reps.setText("0"+Integer.toString(reps));
                }

            }
        });

        bt_mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reps = reps+1;

                if(reps>80){
                    reps=80;
                }

                if(reps>9){
                    num_reps.setText(Integer.toString(reps));
                }

                if(reps<10){
                    num_reps.setText("0"+Integer.toString(reps));
                }

            }
        });

        final Runnable mAction = new Runnable() {
            @Override public void run() {
                reps = reps+1;

                if(reps>80){
                    reps=80;
                }

                if(reps>9){
                    num_reps.setText(Integer.toString(reps));
                }

                if(reps<10){
                    num_reps.setText("0"+Integer.toString(reps));
                }

                mHandler.postDelayed(this, 300);
            }
        };

        final Runnable mAction2 = new Runnable() {
            @Override public void run() {
                reps=reps-1;

                if(reps<1){
                    reps=1;
                }

                if(reps>9){
                    num_reps.setText(Integer.toString(reps));
                }

                if(reps<10){
                    num_reps.setText("0"+Integer.toString(reps));
                }

                mHandler.postDelayed(this, 300);
            }
        };

        bt_mais.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 500);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;

                }

                return false;
            }
        });

        bt_menos.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction2, 500);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction2);
                        mHandler = null;
                        break;

                }

                return false;
            }
        });

        bt_avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proxima_tela();
            }
        });

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_anterior();
            }
        });
        
    }

    private void proxima_tela(){

        Intent intent = new Intent(this,ExeDescansoActivity.class);
        intent.putExtra("eixo",getIntent().getIntExtra("eixo",0));
        intent.putExtra("erro",getIntent().getIntExtra("erro",0));
        intent.putExtra("exercicio",getIntent().getStringExtra("exercicio"));
        intent.putExtra("series",getIntent().getIntExtra("series",0));
        intent.putExtra("reps",reps);
        startActivity(intent);
        finish();

    }

    private void tela_anterior(){

        Intent intent = new Intent(this,ExeSerieActivity.class);
        intent.putExtra("eixo",getIntent().getIntExtra("eixo",0));
        intent.putExtra("erro",getIntent().getIntExtra("erro",0));
        intent.putExtra("exercicio",getIntent().getStringExtra("exercicio"));
        startActivity(intent);
        finish();

    }

}
