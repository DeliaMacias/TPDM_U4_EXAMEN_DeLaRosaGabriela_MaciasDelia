package mx.edu.ittepic.themickyebmo.tpdm_u4_examen_delarosagabriela_maciasdelia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class inter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Lienzo(this,getIntent().getIntExtra("tiempo",10)));
    }
}
