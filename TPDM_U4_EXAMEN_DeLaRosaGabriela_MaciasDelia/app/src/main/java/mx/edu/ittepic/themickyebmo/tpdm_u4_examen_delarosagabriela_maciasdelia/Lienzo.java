package mx.edu.ittepic.themickyebmo.tpdm_u4_examen_delarosagabriela_maciasdelia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Lienzo extends View {
    BitmapFactory life;
    Paint p;
    int width,height,segundos,count_color;
    boolean gano,perdio;
    int yMax,xMax;
    int[] correctos;
    int puntos;
    int ronda;
    int vidas;
    CountDownTimer decometro;
    Random valores,colores;
    int[] ArrayColor;
    int[] Colores;
    String[] F_Color;
    int [][] posiciones;
    Frases[][] array= new Frases[5][6];
    int rango;
    int col;
    int[] corazones;



    public Lienzo(Context context,int time) {
        super(context);
        gano=perdio=false;
        width = getResources().getSystem().getDisplayMetrics().widthPixels;
        height = getResources().getSystem().getDisplayMetrics().heightPixels-200;
        correctos = new int[5];
        puntos = 0;
        ronda = 0;
        corazones = new int[]{R.drawable.kora3, R.drawable.kora2, R.drawable.kora1};
        vidas=0;
        //VECTOR  DE LAS IMAGENES A SELECCIONAR
        ArrayColor = new int[]{R.drawable.mora, R.drawable.azul,R.drawable.ama,R.drawable.rojo,R.drawable.rosa,R.drawable.verde};count_color=0;
        F_Color = new String[]{"MORADO","AZUL","AMARILLO","ROJO","ROSA","VERDE"};
        Colores = new int[]{
                Color.rgb(143,0,143), //morado
                Color.rgb(0,204,255), //azul
                Color.rgb(255,255,0), //amarillo
                Color.rgb(255,0,0), //rojo
                Color.rgb(255,51,153), //rosa
                Color.rgb(22,224,45)}; //verde

        posiciones = new int[][]{
                {width/4-100,height*3/8},
                {width*3/4-100,height*3/8},
                {width/4-100,height*5/8},
                {width*3/4-100,height*5/8},
                {width/4-100,height*7/8},
                {width*3/4-100,height*7/8}
        };
        valores = new Random();
        colores = new Random();
        rango = 0+valores.nextInt(5);
        segundos=time;

        for (int i =0; i< 5; i++){
            for (int y =0; y< 6; y++){
                if(y == rango) {
                    array[i][y] = new Frases(this, ArrayColor[y], posiciones[y][0], posiciones[y][1], true);
                    correctos[i] = y;
                } else {
                    array[i][y] = new Frases(this, ArrayColor[y], posiciones[y][0], posiciones[y][1], false);
                }
            }
            rango = 0+valores.nextInt(5);
        }
        decometro = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(!perdio){
                    if (segundos < 1) {
                        perdio = true;
                        invalidate();
                    } else {
                        segundos--;
                        invalidate();
                        start();
                    }
                }

            }
        };



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        if(gano) {
            canvas.drawColor(Color.WHITE);
            p.setTextSize(120);
            canvas.drawText("GANASTE", width / 4, height / 2, p);
        } else {
            if (perdio) {
                canvas.drawColor(Color.WHITE);
                p.setTextSize(120);
                canvas.drawText("PERDISTE", width / 4, height / 2, p);
            } else {
                canvas.drawColor(Color.WHITE);
                if (vidas < 3) {
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), corazones[vidas]), 1, 1, p);
                }
                //Genera la palabra
                p.setColor(Colores[col]);
                p.setTextSize(120);
                canvas.drawText(F_Color[correctos[ronda]], width / 2, 100, p);
                for (int i = 0; i < 6; i++) {
                    canvas.drawBitmap(array[ronda][i].imagen, array[ronda][i].x, array[ronda][i].y, p);
                }
                /*DECOMETRO*/
                p.setColor(Color.BLACK);
                p.setTextSize(100);
                canvas.drawText(segundos + "", 10, height / 8, p);
                canvas.drawText(puntos + "", width / 2, height / 8, p);
                decometro.start();
            }
        }
    }

    public void cambiaColor(){
        col = 0+colores.nextInt(5);
    }

    public boolean onTouchEvent(MotionEvent me){
        int accion = me.getAction();
        int posx = (int)me.getX();
        int posy = (int)me.getY();

        switch(accion){
            case MotionEvent.ACTION_DOWN: //presiono
                if (array[ronda][correctos[ronda]].estaEnArea(posx, posy) && puntos<5) {
                    puntos++;
                    if (puntos > 4) {
                        gano = true;
                    }
                } else {
                    vidas++;
                }
                if (ronda <4) {
                    ronda++;
                    cambiaColor();
                    invalidate();
                }
                if(vidas>2 || segundos<1) {
                    perdio = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    public void onSizeChanged(int w,int h,int oldW,int oldH){
        xMax = w-1;
        yMax = h-1;
    }
}
