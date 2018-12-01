package com.example.luanp.birl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private ImageButton bt_exercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bt_exercicio = (ImageButton) findViewById(R.id.bt_exercicio);

        bt_exercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proxtela();
            }
        });

    }

    private void proxtela(){

        Intent intent = new Intent(this,ExeActivity.class);
        startActivity(intent);
        finish();

    }

}
