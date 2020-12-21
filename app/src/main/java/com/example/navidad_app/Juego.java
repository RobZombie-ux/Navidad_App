package com.example.navidad_app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class Juego extends Activity {

    ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11, imb12, imb13, imb14, imb15;
    ImageButton[] tablero = new ImageButton[16];

    Button buttonReiniciar, buttonSalir;
    TextView tvPuntos;
    int puntos;
    int aciertos;

    //Imagenes
    int[] imagenes;
    int fondo;

    //variables del juego
    ArrayList<Integer> arrayAleatorio;
    ImageButton first;
    int numFirst, numSecond;
    boolean block = false;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        iniciar();

    }

    private void cargarTablero() {
        imb00 = findViewById(R.id.button00);
        imb01 = findViewById(R.id.button01);
        imb02 = findViewById(R.id.button02);
        imb03 = findViewById(R.id.button03);
        imb04 = findViewById(R.id.button04);
        imb05 = findViewById(R.id.button05);
        imb06 = findViewById(R.id.button06);
        imb07 = findViewById(R.id.button07);
        imb08 = findViewById(R.id.button08);
        imb09 = findViewById(R.id.button09);
        imb10 = findViewById(R.id.button10);
        imb11 = findViewById(R.id.button11);
        imb12 = findViewById(R.id.button12);
        imb13 = findViewById(R.id.button13);
        imb14 = findViewById(R.id.button14);
        imb15 = findViewById(R.id.button15);

        //Posiciones
        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
        tablero[10] = imb10;
        tablero[11] = imb11;
        tablero[12] = imb12;
        tablero[13] = imb13;
        tablero[14] = imb14;
        tablero[15] = imb15;
    }

    private void cargarBotones() {
        buttonReiniciar = (Button) findViewById(R.id.btnReiniciar);
        buttonSalir = (Button) findViewById(R.id.btnSalir_Juego);

        //Metodos
        buttonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });

        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void cargarTexto() {
        tvPuntos = (TextView) findViewById(R.id.txtPuntos);
        puntos = 0;
        aciertos = 0;
        tvPuntos.setText("Intentos: " + puntos);
    }

    private void cargarImagenes() {
        imagenes = new int[]{
                R.drawable.img0,
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3,
                R.drawable.img4,
                R.drawable.img5,
                R.drawable.img6,
                R.drawable.img7
        };
        fondo = R.drawable.fondo;
    }

    private ArrayList<Integer> combinar(int longitud) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < longitud * 2; i++) {
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        //System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void comprobar(int i, final ImageButton imgb){
        if(first == null){
            first = imgb;
            first.setScaleType(ImageView.ScaleType.CENTER_CROP);
            first.setImageResource(imagenes[arrayAleatorio.get(i)]);
            first.setEnabled(false);
            numFirst = arrayAleatorio.get(i);
        } else{
            block = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arrayAleatorio.get(i)]);
            imgb.setEnabled(false);
            numSecond = arrayAleatorio.get(i);

            //Comparar cartas
            if(numFirst == numSecond){
                first = null;
                block = false;
                aciertos++;
                puntos++;
                tvPuntos.setText("Intentos: " + puntos);

                //Comprobar si gana
                if(aciertos == imagenes.length){
                    //Luego enviar a la siguiente vista
                    Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else{
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        first.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        first.setImageResource(fondo);
                        first.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(fondo);
                        imgb.setEnabled(true);
                        block = false;
                        first = null;
                        puntos++;
                        tvPuntos.setText("Intentos: " + puntos);
                    }
                }, 1000);
            }
        }
    }

    private void iniciar() {
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayAleatorio = combinar(imagenes.length);
        for (int i = 0; i < tablero.length; i++) {
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            //asignar imagen
            tablero[i].setImageResource(imagenes[arrayAleatorio.get(i)]);
        }
        //Delay
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tablero.length; i++) {
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 500);

        for(int i=0; i< tablero.length; i++){
            final int f = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!block)
                        comprobar(f, tablero[f]);
                }
            });
        }
    }
}
