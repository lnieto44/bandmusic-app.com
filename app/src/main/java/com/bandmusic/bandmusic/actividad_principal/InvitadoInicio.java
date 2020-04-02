package com.bandmusic.bandmusic.actividad_principal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bandmusic.bandmusic.Paneles_Laterales.Criterios_Evaluar;
import com.bandmusic.bandmusic.R;

public class InvitadoInicio extends AppCompatActivity implements View.OnClickListener {

    Button criterios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitado_inicio);

        criterios = (Button) findViewById(R.id.button5);

        criterios.setOnClickListener(this);

        Button urlBM  = (Button) findViewById(R.id.button3);

        urlBM.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        Intent explicit_intent;//Declaro el Intent

        switch (v.getId()){//Obtengo el ID del boton precionado

            case R.id.button5://Si el ID es btn

                //Instanciamos el Intent dandole:
                // el contexto y la clase a la cual nos deseamos dirigir
                explicit_intent = new Intent(this,VisualizarCriterios.class);

                //lo iniciamos pasandole la intencion
                startActivity(explicit_intent);
                break;

            case R.id.button3://Si el ID es btn
                Uri uri = Uri.parse("http://bandmusicweb.epizy.com/");
                explicit_intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(explicit_intent);
                break;
        }
    }
}

