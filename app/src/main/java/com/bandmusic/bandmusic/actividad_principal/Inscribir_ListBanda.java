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
import com.bandmusic.bandmusic.adaptadores.RegistrarBandaAdapter;
import com.bandmusic.bandmusic.modelos.Bandas;
import com.bandmusic.bandmusic.base_datos_SQL.BandasDatabaseHelper;
import java.util.ArrayList;


public class Inscribir_ListBanda extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private AppCompatActivity activity = Inscribir_ListBanda.this;
    Context context = Inscribir_ListBanda.this;
    private RecyclerView recyclerViewBandas;
    private ArrayList<Bandas> listBandas;
    private RegistrarBandaAdapter registrarBandaAdapter;
    private BandasDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscribir_list_banda);
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
        recyclerViewBandas= (RecyclerView) findViewById(R.id.recyclerViewBandas);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listBandas = new ArrayList<>();
        registrarBandaAdapter = new RegistrarBandaAdapter(listBandas, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewBandas.setLayoutManager(mLayoutManager);
        recyclerViewBandas.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBandas.setHasFixedSize(true);
        recyclerViewBandas.setAdapter(registrarBandaAdapter);
        databaseHelper = new BandasDatabaseHelper(activity);

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
        ArrayList<Bandas> newList = new ArrayList<>();
        for (Bandas user: listBandas){
            String name = user.getNombre().toLowerCase();
            if (name.contains(newText)){
                newList.add(user);
            }
        }

        registrarBandaAdapter.setFilter(newList);
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
                listBandas.clear();
                listBandas.addAll(databaseHelper. getAllBandas());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                registrarBandaAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
