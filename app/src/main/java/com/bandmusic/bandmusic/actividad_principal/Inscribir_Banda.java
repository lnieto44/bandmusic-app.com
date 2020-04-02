package com.bandmusic.bandmusic.actividad_principal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.ayudas.InputValidationRegistrarBanda;
import com.bandmusic.bandmusic.modelos.Bandas;

import com.bandmusic.bandmusic.base_datos_SQL.BandasDatabaseHelper;


public class Inscribir_Banda extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = Inscribir_Banda.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutNombreBanda;
    private TextInputLayout textInputLayoutOrganizacion;
    private TextInputLayout textInputLayoutCiudad;
    private TextInputLayout textInputLayoutCategoria;

    private TextInputEditText textInputEditTextNombreBanda;
    private TextInputEditText textInputEditTextOrganizacion;
    private TextInputEditText textInputEditTextCiudad;
    private TextInputEditText textInputEditTextCategoria;


    private AppCompatButton appCompatButtonRegister;

    private InputValidationRegistrarBanda inputValidationRegistrarBanda;
    private BandasDatabaseHelper bandasDatabaseHelper;
    private Bandas bandas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscribir_banda);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        initObjects();
        initListeners();
    }

    //Initialize Views
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutNombreBanda = (TextInputLayout) findViewById(R.id.textInputLayoutNombreBanda);
        textInputLayoutOrganizacion = (TextInputLayout) findViewById(R.id.textInputLayoutOrganizacion);
        textInputLayoutCiudad = (TextInputLayout) findViewById(R.id.textInputLayoutCiudad);
        textInputLayoutCategoria = (TextInputLayout) findViewById(R.id.textInputLayoutCategoria);

        textInputEditTextNombreBanda = (TextInputEditText) findViewById(R.id.textInputEditTextNombreBanda);
        textInputEditTextOrganizacion = (TextInputEditText) findViewById(R.id.textInputEditTextOrganizacion);
        textInputEditTextCiudad = (TextInputEditText) findViewById(R.id.textInputEditTextCiudad);
        textInputEditTextCategoria= (TextInputEditText) findViewById(R.id.textInputEditTextCategoria);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidationRegistrarBanda = new InputValidationRegistrarBanda(activity);
        bandasDatabaseHelper = new BandasDatabaseHelper(activity);
        bandas = new Bandas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidationRegistrarBanda.isInputEditTextFilled(textInputEditTextNombreBanda, textInputLayoutNombreBanda, getString(R.string.error_message_nombre_banda))) {
            return;
        }
        if (!inputValidationRegistrarBanda.isInputEditTextFilled(textInputEditTextOrganizacion, textInputLayoutOrganizacion, getString(R.string.error_message_organizacion))) {
            return;
        }
        if (!inputValidationRegistrarBanda.isInputEditTextFilled(textInputEditTextCiudad, textInputLayoutCiudad, getString(R.string.error_message_ciudad))) {
            return;
        }
        if (!inputValidationRegistrarBanda.isInputEditTextFilled(textInputEditTextCategoria, textInputLayoutCategoria, getString(R.string.error_message_categoria))) {
            return;
        }
        if (!bandasDatabaseHelper.checkUser(textInputEditTextOrganizacion.getText().toString().trim())) {

            bandas.setNombre(textInputEditTextNombreBanda.getText().toString().trim());
            bandas.setOrganizacion(textInputEditTextOrganizacion.getText().toString().trim());
            bandas.setCiudad(textInputEditTextCiudad.getText().toString().trim());
            bandas.setCategoria(textInputEditTextCategoria.getText().toString().trim());


            bandasDatabaseHelper.addBandas(bandas);

            // Snack Bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(activity, Inscribir_ListBanda.class);
            Toast.makeText(this, "Registro Exitoso!", Toast.LENGTH_SHORT)
                    .show();

            emptyInputEditText();
            startActivity(accountsIntent);

        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    private void emptyInputEditText() {
        textInputEditTextNombreBanda.setText(null);
        textInputEditTextOrganizacion.setText(null);
        textInputEditTextCiudad.setText(null);
        textInputEditTextCategoria.setText(null);
    }
}