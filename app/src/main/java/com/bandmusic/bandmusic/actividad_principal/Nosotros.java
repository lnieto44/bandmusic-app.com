package com.bandmusic.bandmusic.actividad_principal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.adaptadores.RecyclerViewAdaptador;
import com.bandmusic.bandmusic.modelos.Sobre_Nosotros;

import java.util.ArrayList;

public class Nosotros extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvDatos;
    private Activity activity;
    private RecyclerViewAdaptador adaptador;
    private ArrayList<Sobre_Nosotros> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosotros);
        lvDatos = (ListView) findViewById(R.id.lvLista);
        activity = this;
        datos = new ArrayList<Sobre_Nosotros>();

        llenarArrayList();

        adaptador = new RecyclerViewAdaptador(activity, datos);

        lvDatos.setAdapter(adaptador);
        lvDatos.setOnItemClickListener(this);

    }

    public void llenarArrayList() {
        Resources resources = getResources();
        String[] arrayNombres = resources.getStringArray(R.array.nombre);
        String[] arrayProfesion = resources.getStringArray(R.array.profesion);
        String[] arrayCorreo = resources.getStringArray(R.array.correo);
        String[] arrayCelular = resources.getStringArray(R.array.celular);
        TypedArray imgs = resources.obtainTypedArray(R.array.imagen);
        for(int i=0; i < arrayNombres.length; i++) {
            datos.add(new Sobre_Nosotros(arrayNombres[i], arrayProfesion[i], arrayCelular[i],
                    arrayCorreo[i], imgs.getResourceId(i, -1)));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Exitoso", Toast.LENGTH_LONG).show();
        Sobre_Nosotros nosotros = datos.get(position);
        Intent i = new Intent(getApplicationContext(), DetalleSobreNosotros.class);
        i.putExtra("nosotros", nosotros);
        startActivity(i);
    }
}

