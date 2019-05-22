package mx.edu.ittepic.themickyebmo.tpdm_u4_examen_delarosagabriela_maciasdelia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Lienzo extends View {
    BitmapFactory life;
    Paint p;
    int width,height,segundos,count_color,count_figura;
    boolean activo,gano,perdio;
    int yMax,xMax;

    CountDownTimer decometro;
    Random posicion;

    Frases[] ArrayColor,ArrayFigura;
    String[] F_Color,F_Figura;


    public Lienzo(Context context) {
        super(context);

        activo=true;
        gano=perdio=false;

        //VECTOR  DE LAS IMAGENES A SELECCIONAR
        ArrayColor = new Frases[5];count_color=0;
        ArrayFigura = new Frases[5];count_figura=0;

        //PALABRAS
        F_Color = new String[5];
        F_Figura = new String[5];


        posicion = new Random();

        segundos=15;

        width = getResources().getSystem().getDisplayMetrics().widthPixels;
        height = getResources().getSystem().getDisplayMetrics().heightPixels-200;



        for (int i =0; i< ArrayColor.length; i++){
            ArrayColor[i] = new Frases(this,R.drawable.ama,(1+posicion.nextInt(width-730)),(290+posicion
                    .nextInt(height-250)));
            ArrayColor[i] = new Frases(this,R.drawable.azul,(1+posicion.nextInt(width-730)),(290+posicion
                    .nextInt(height-250)));
        }
        for (int y =0; y< ArrayFigura.length; y++){
            ArrayFigura[y] = new Frases(this,R.drawable.tama,(1+posicion.nextInt(width-730)),(1+posicion
                    .nextInt(height-900)));

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
        Bitmap life;
        /*3 VIDAS*/
        life = BitmapFactory.decodeResource(getResources(),R.drawable.kora3);

       /* Bitmap life2 =life.copy(Bitmap.Config.ARGB_8888,true);
        life2.setWidth(width-200);
        //life.setHeight(height);*/
        canvas.drawBitmap(life,1,1,p);

        if (perdio && !gano) {
            p.setColor(Color.BLACK);
            p.setTextSize(100);
            canvas.drawText("LO SIENTO, PERDISTE!!",width/2-525,height/2,p);
        }else{
            if(gano){
                p.setColor(Color.BLACK);
                p.setTextSize(100);
                canvas.drawText("Felicidades, GANASTE!!",width/2-525,height/2,p);
            }else {
                if (activo) {

                    canvas.drawBitmap(ArrayColor[count_color].imagen, ArrayColor[count_color].x, ArrayColor[count_color].y, p);
                    ArrayColor[count_color].pintar(canvas, p);

                } else {
                    canvas.drawBitmap(ArrayFigura[count_figura].imagen, ArrayFigura[count_figura].x, ArrayFigura[count_figura].y, p);
                    ArrayFigura[count_figura].pintar(canvas, p);
                    if (count_figura == ArrayFigura.length - 1) {
                        gano = true;
                    }
                }
            }
        }






        /*DECOMETRO*/
        p.setColor(Color.BLACK);
        p.setTextSize(100);
        canvas.drawText(segundos+"",width/2,100,p);
        decometro.start();

    }

    public boolean onTouchEvent(MotionEvent me){
        int accion = me.getAction();
        int posx = (int)me.getX();
        int posy = (int)me.getY();

        switch(accion){
            case MotionEvent.ACTION_DOWN: //presiono
                if (!gano) {
                    if (activo && ArrayColor[count_color].estaEnArea(posx, posy)) {
                        if (count_color < ArrayColor.length) {
                            if (count_color == ArrayColor.length - 1) {
                                activo = false;
                                segundos = 10;
                            }
                            count_color++;
                        }
                    }
                    /*if (!activo && ArrayColor[jefe_muerte].estaEnArea(posx, posy)) {
                        if (jefe_muerte < arrayMoscaJefe.length) {
                            jefe_muerte++;
                        }
                    }*/
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
