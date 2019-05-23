package mx.edu.ittepic.themickyebmo.tpdm_u4_examen_delarosagabriela_maciasdelia;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class Eleccion extends AppCompatActivity {
    Button play,config;
    Switch nivel;
    AlertDialog.Builder info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion);

        play = findViewById(R.id.play);
        nivel = findViewById(R.id.level);
        config = findViewById(R.id.config);

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = new AlertDialog.Builder(Eleccion.this);
                info.setTitle("Instrucciones");
                info.setMessage("El usuario elige el nivel del juego, donde al hacerlo podrá jugar con una cantidad variada de segundos.El usuario debe acertar el color correspondiente a la palabra." +
                        "\n \n \n Aplicación realizada por: \n Delia Macias Urzua\n Gabriela De La Rosa");
                info.setPositiveButton("De acuerdo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface info, int id) {

                    }
                });
                info.show();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Eleccion.this,inter.class);
                if(nivel.isChecked()){
                    i.putExtra("tiempo",5);
                }else{
                    i.putExtra("tiempo",10);
                }
                startActivity(i);
            }
        });


    }
}
