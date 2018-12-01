package com.example.luanp.birl;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ExeDescansoActivity extends AppCompatActivity {

    private ImageButton bt_menos;
    private ImageButton bt_mais;
    private ImageButton bt_avancar;
    private ImageButton bt_voltar;
    private TextView num_segs;
    private int segs = 5;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exe_descanso);

        bt_menos = (ImageButton) findViewById(R.id.imageButton9);
        bt_mais = (ImageButton) findViewById(R.id.imageButton10);
        bt_avancar = (ImageButton) findViewById(R.id.imageButton14);
        bt_voltar = (ImageButton) findViewById(R.id.imageButton13);
        num_segs = (TextView) findViewById(R.id.textView14);

        num_segs.setText("0"+Integer.toString(segs));

        bt_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                segs=segs-1;

                if(segs<5){
                    segs=5;
                }

                if(segs>9){
                    num_segs.setText(Integer.toString(segs));
                }

                if(segs<10){
                    num_segs.setText("0"+Integer.toString(segs));
                }

            }
        });

        bt_mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                segs = segs+1;

                if(segs>40){
                    segs=40;
                }

                if(segs>9){
                    num_segs.setText(Integer.toString(segs));
                }

                if(segs<10){
                    num_segs.setText("0"+Integer.toString(segs));
                }

            }
        });

        final Runnable mAction = new Runnable() {
            @Override public void run() {
                segs = segs+1;

                if(segs>40){
                    segs=40;
                }

                if(segs>9){
                    num_segs.setText(Integer.toString(segs));
                }

                if(segs<10){
                    num_segs.setText("0"+Integer.toString(segs));
                }

                mHandler.postDelayed(this, 300);
            }
        };

        final Runnable mAction2 = new Runnable() {
            @Override public void run() {
                segs=segs-1;

                if(segs<5){
                    segs=5;
                }

                if(segs>9){
                    num_segs.setText(Integer.toString(segs));
                }

                if(segs<10){
                    num_segs.setText("0"+Integer.toString(segs));
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

        Intent intent = new Intent(this,ExeActionActivity.class);
        intent.putExtra("eixo",getIntent().getIntExtra("eixo",0));
        intent.putExtra("erro",getIntent().getIntExtra("erro",0));
        intent.putExtra("exercicio",getIntent().getStringExtra("exercicio"));
        intent.putExtra("reps",getIntent().getIntExtra("reps",1));
        intent.putExtra("series",getIntent().getIntExtra("series",1));
        intent.putExtra("segs",segs);
        startActivity(intent);
        finish();

    }

    private void tela_anterior(){

        Intent intent = new Intent(this,ExeRepsActivity.class);
        intent.putExtra("eixo",getIntent().getIntExtra("eixo",0));
        intent.putExtra("erro",getIntent().getIntExtra("erro",0));
        intent.putExtra("exercicio",getIntent().getStringExtra("exercicio"));
        intent.putExtra("series",getIntent().getIntExtra("series",1));
        startActivity(intent);
        finish();

    }
    
}
