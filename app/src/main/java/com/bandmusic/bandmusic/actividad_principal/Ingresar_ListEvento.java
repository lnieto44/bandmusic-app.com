package com.bandmusic.bandmusic.actividad_principal;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.adaptadores.CrearEventoAdapter;
import com.bandmusic.bandmusic.modelos.CrearEventos;
import com.bandmusic.bandmusic.base_datos_SQL.CrearEventoDatabaseHelper;
import java.util.ArrayList;


public class Ingresar_ListEvento extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private AppCompatActivity activity = Ingresar_ListEvento.this;
    Context context = Ingresar_ListEvento.this;
    private RecyclerView recyclerViewEventos;
    private ArrayList<CrearEventos> listEventos;
    private CrearEventoAdapter crearEventoAdapter;
    private CrearEventoDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_evento_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        initObjects();

    }



    /**
     * This method is to initialize views
     */
    private void initViews() {
        recyclerViewEventos= (RecyclerView) findViewById(R.id.recyclerViewEventos);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listEventos = new ArrayList<>();
        crearEventoAdapter = new CrearEventoAdapter(listEventos, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewEventos.setLayoutManager(mLayoutManager);
        recyclerViewEventos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEventos.setHasFixedSize(true);
        recyclerViewEventos.setAdapter(crearEventoAdapter);
        databaseHelper = new CrearEventoDatabaseHelper(activity);

        getDataFromSQLite();

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText){
        newText = newText.toLowerCase();
        ArrayList<CrearEventos> newList = new ArrayList<>();
        for (CrearEventos user: listEventos){
            String name = user.getNombre().toLowerCase();
            if (name.contains(newText)){
                newList.add(user);
            }
        }

        crearEventoAdapter.setFilter(newList);
        return true;

    }




    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listEventos.clear();
                listEventos.addAll(databaseHelper. getAllEventos());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                crearEventoAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}

