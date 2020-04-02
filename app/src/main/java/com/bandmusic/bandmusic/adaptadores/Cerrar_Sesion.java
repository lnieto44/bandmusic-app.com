package com.bandmusic.bandmusic.adaptadores;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bandmusic.bandmusic.*;
import com.bandmusic.bandmusic.actividad_principal.InicioPrincipal;
import com.bandmusic.bandmusic.actividad_principal.LoginActivity;

public class Cerrar_Sesion extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(Cerrar_Sesion.this,  InicioPrincipal.class);
        startActivity(intent);

    }


}
