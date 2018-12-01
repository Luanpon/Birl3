package com.example.luanp.birl;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Runnable{

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        handler.postDelayed(this,900);

    }

    @Override
    public void run() {

        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
        finish();

    }

}
