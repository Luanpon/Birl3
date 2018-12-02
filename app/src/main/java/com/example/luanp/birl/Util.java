package com.example.luanp.birl;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Carlos David on 02/12/2018.
 */

public class Util {

    public enum som{
      ENTRADA,
      CONTAGEM,
      INICIO,
      FINALIZACAO
    };

    public static void TocarSom(Context context,Enum som){
        MediaPlayer mp;
        if(som == Util.som.CONTAGEM) {
            mp = MediaPlayer.create(context, R.raw.beep);
             mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                 @Override
                 public void onCompletion(MediaPlayer mp) {
                    mp.release();
                 }
             });
            mp.start();
        }
        else if(som == Util.som.ENTRADA){
            mp = MediaPlayer.create(context, R.raw.beep);
           // mp.start();
        }
        else if(som == Util.som.FINALIZACAO){
            mp = MediaPlayer.create(context, R.raw.beep);
          //  mp.start();
        }
        else if(som == Util.som.INICIO){
            mp = MediaPlayer.create(context, R.raw.beep);
            //mp.start();
        }


        }
}
