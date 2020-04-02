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
import com.bandmusic.bandmusic.adaptadores.JuradosEventoAdapter;
import com.bandmusic.bandmusic.modelos.JuradosEvento;
import com.bandmusic.bandmusic.base_datos_SQL.JuradosEventoDatabaseHelper;
import java.util.ArrayList;


public class Jurados_ListEvento extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private AppCompatActivity activity = Jurados_ListEvento.this;
    Context context = Jurados_ListEvento.this;
    private RecyclerView recyclerViewJurados;
    private ArrayList<JuradosEvento> listJurados;
    private JuradosEventoAdapter juradosEventoAdapter;
    private JuradosEventoDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurados_list_evento);
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
        recyclerViewJurados= (RecyclerView) findViewById(R.id.recyclerViewJurados);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listJurados = new ArrayList<>();
        juradosEventoAdapter = new JuradosEventoAdapter(listJurados, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewJurados.setLayoutManager(mLayoutManager);
        recyclerViewJurados.setItemAnimator(new DefaultItemAnimator());
        recyclerViewJurados.setHasFixedSize(true);
        recyclerViewJurados.setAdapter(juradosEventoAdapter);
        databaseHelper = new JuradosEventoDatabaseHelper(activity);

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
        ArrayList<JuradosEvento> newList = new ArrayList<>();
        for (JuradosEvento user: listJurados){
            String name = user.getNombre().toLowerCase();
            if (name.contains(newText)){
                newList.add(user);
            }
        }

        juradosEventoAdapter.setFilter(newList);
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
                listJurados.clear();
                listJurados.addAll(databaseHelper. getAllJurados());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                juradosEventoAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
