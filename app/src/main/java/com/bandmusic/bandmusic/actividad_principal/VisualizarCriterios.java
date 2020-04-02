package com.bandmusic.bandmusic.actividad_principal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bandmusic.bandmusic.R;
public class VisualizarCriterios extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_criterios);

        btn1 = (Button)findViewById(R.id.button3);
        btn2 = (Button)findViewById(R.id.button4);
        btn3 = (Button)findViewById(R.id.button5);
        btn4 = (Button)findViewById(R.id.button6);
        btn5 = (Button)findViewById(R.id.button7);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent explicit_intent;//Declaro el Intent

        switch (v.getId()){//Obtengo el ID del boton precionado

            case R.id.button3://Si el ID es btn

                //Instanciamos el Intent dandole:
                // el contexto y la clase a la cual nos deseamos dirigir
                explicit_intent = new Intent(this,Afinacion_Interpretacion.class);

                //lo iniciamos pasandole la intencion
                startActivity(explicit_intent);
                break;

            case R.id.button4://Si el ID es btn
                explicit_intent = new Intent(this, Coreografia_Percusion.class);
                startActivity(explicit_intent);
                break;

            case R.id.button5://Si el ID es btn
                explicit_intent = new Intent(this, Manejo_Impacto_Visual.class);
                startActivity(explicit_intent);
                break;

            case R.id.button6://Si el ID es btn
                explicit_intent = new Intent(this, Salida.class);
                startActivity(explicit_intent);
                break;

            case R.id.button7://Si el ID es btn
                explicit_intent = new Intent(this, Recorrido.class);
                startActivity(explicit_intent);
                break;

        }
    }
}