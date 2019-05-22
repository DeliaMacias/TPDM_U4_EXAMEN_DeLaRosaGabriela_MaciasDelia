package mx.edu.ittepic.themickyebmo.tpdm_u4_examen_delarosagabriela_maciasdelia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Frases {

    Bitmap imagen;
    int x,y;
    boolean visible;

    public Frases(Lienzo lienzo, int imagen, int x, int y) {
        this.imagen=BitmapFactory.decodeResource(lienzo.getResources(),imagen);
        this.x = x;
        this.y = y;
        visible = false;
    }


    public void pintar(final Canvas canvas,Paint p){
        canvas.drawBitmap(imagen,x,y,p);
    }

    public boolean estaEnArea(int Xdedo, int Ydedo){
        int x2 = x+imagen.getWidth();
        int y2 = y+imagen.getHeight();

        if(Xdedo >= x && Xdedo <= x2){
            if(Ydedo >= y && Ydedo <= y2){
                return true;
            }
        }
        return false;

    }

}
