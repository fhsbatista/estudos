package com.devandroid.fbatista.entendendothreads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private int contador;

    private Button iniciarThread;

    private boolean pararExecucao = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarThread = findViewById(R.id.bt_iniciar_thread);
    }

    public void iniciarThread(View v) {
        pararExecucao = false;
        MyRunnable runnable = new MyRunnable();
        new Thread( runnable ).start();



    }

    public void pararThread(View v){

        pararExecucao = true;

    }

    class MyRunnable implements Runnable {


        @Override
        public void run() {




            for (int i = 0; i < 15; i++) {

                if(pararExecucao)
                    return;

                contador = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iniciarThread.setText("contador: " + contador);
                    }
                });

                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }




            }
        }
    }
}
