package com.bandmusic.bandmusic.Paneles_Laterales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bandmusic.bandmusic.*;
import com.bandmusic.bandmusic.adaptadores.Cerrar_Sesion;
import com.bandmusic.bandmusic.actividad_principal.Jurados_Evento;
import com.bandmusic.bandmusic.actividad_principal.Jurados_ListActivity;
import com.bandmusic.bandmusic.actividad_principal.Jurados_ListEvento;
import com.bandmusic.bandmusic.actividad_principal.RegistroJuradosActivity;

public class Asignar_Jurado extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ImageView imgA,imgC,imgD,imgE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_asignar__jurado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imgA=(ImageView)findViewById(R.id.imageView);
        imgC=(ImageView)findViewById(R.id.imageView2);
        imgD=(ImageView)findViewById(R.id.imageView5);
        imgE=(ImageView)findViewById(R.id.imageView6);

        imgA.setOnClickListener(this);
        imgC.setOnClickListener(this);
        imgD.setOnClickListener(this);
        imgE.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent explicit_intent;//Declaro el Intent

        switch (v.getId()){//Obtengo el ID del boton precionado

            case R.id.imageView://Si el ID es btn

                //Instanciamos el Intent dandole:
                // el contexto y la clase a la cual nos deseamos dirigir
                explicit_intent = new Intent(this,RegistroJuradosActivity.class);

                //lo iniciamos pasandole la intencion
                startActivity(explicit_intent);
                break;

            case R.id.imageView2://Si el ID es btn
                explicit_intent = new Intent(this, Jurados_Evento.class);
                startActivity(explicit_intent);
                break;

            case R.id.imageView5://Si el ID es btn
                explicit_intent = new Intent(this, Jurados_ListActivity.class);
                startActivity(explicit_intent);
                break;

            case R.id.imageView6://Si el ID es btn
                explicit_intent = new Intent(this, Jurados_ListEvento.class);
                startActivity(explicit_intent);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.asignar_jurados) {
            Intent searchIntent = new Intent(Asignar_Jurado.this, MainActivityAdmin.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.registrar_banda) {
            Intent searchIntent = new Intent(Asignar_Jurado.this, Registrar_Banda.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.crear_evento) {
            Intent searchIntent = new Intent(Asignar_Jurado.this,Crear_Eventos.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.generar_reporte) {
            Intent searchIntent = new Intent(Asignar_Jurado.this,Registrar_Banda.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.criterios_evaluadados) {
            Intent searchIntent = new Intent(Asignar_Jurado.this,Criterios_Evaluar.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.nav_nosotros) {

        } else if (id == R.id.nav_log_out) {
            Intent searchIntent = new Intent(Asignar_Jurado.this,Cerrar_Sesion.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

