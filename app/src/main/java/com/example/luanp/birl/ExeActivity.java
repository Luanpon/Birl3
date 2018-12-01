package com.example.luanp.birl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExeActivity extends AppCompatActivity {

    private ArrayList<String> lista_exercicios = new ArrayList<String>();
    private ArrayList<Integer> lista_eixos = new ArrayList<Integer>();
    private ArrayList<Integer> lista_erros = new ArrayList<Integer>();

    private ImageButton bt_anterior;
    private ImageButton bt_proximo;
    private ImageButton bt_avancar;
    private ImageButton bt_voltar;

    private TextView exercicio_selecionado;

    private int indice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exe);

        bt_anterior = (ImageButton) findViewById(R.id.bt_anterior);
        bt_proximo = (ImageButton) findViewById(R.id.bt_proximo);
        bt_avancar = (ImageButton) findViewById(R.id.bt_avancar);
        bt_voltar = (ImageButton) findViewById(R.id.bt_voltar);

        exercicio_selecionado = (TextView) findViewById(R.id.exercicio_selecionado);

        insere("Rosca Direta",1,10);
        insere("Supino Reto",2,10);

        exercicio_selecionado.setText(lista_exercicios.get(indice));

        bt_anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                indice = indice-1;

                if(indice<0){
                    indice = lista_exercicios.size()-1;
                }

                exercicio_selecionado.setText(lista_exercicios.get(indice));

            }
        });

        bt_proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                indice = indice+1;

                if(indice>(lista_exercicios.size()-1)){
                    indice=0;
                }

                exercicio_selecionado.setText(lista_exercicios.get(indice));

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

                voltar_menu();

            }
        });

    }

    private void insere(String exercio, int eixo, int erro){

        lista_exercicios.add(exercio);
        lista_eixos.add(eixo);
        lista_erros.add(erro);

    }

    private void proxima_tela(){

        if(indice==0){
            Intent intent = new Intent(this,ExeSerieActivity.class);
            intent.putExtra("eixo",lista_eixos.get(indice));
            intent.putExtra("erro",lista_erros.get(indice));
            intent.putExtra("exercicio",lista_exercicios.get(indice));
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Exercício não disponível. Favor escolha outro.", Toast.LENGTH_LONG).show();
        }

    }

    private void voltar_menu(){

        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
        finish();

    }

}
