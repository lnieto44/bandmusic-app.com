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
import com.bandmusic.bandmusic.ayudas.InputValidationCrearEvento;
import com.bandmusic.bandmusic.modelos.CrearEventos;

import com.bandmusic.bandmusic.base_datos_SQL.CrearEventoDatabaseHelper;


public class Ingresar_Eventos extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = Ingresar_Eventos.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEvento;
    private TextInputLayout textInputLayoutNombre;
    private TextInputLayout textInputLayoutCategoria;
    private TextInputLayout textInputLayoutCiudad;

    private TextInputEditText textInputEditTextEvento;
    private TextInputEditText textInputEditTextNombre;
    private TextInputEditText textInputEditTextCategoria;
    private TextInputEditText textInputEditTextCiudad;


    private AppCompatButton appCompatButtonRegister;

    private InputValidationCrearEvento inputValidationCrearEvento;
    private CrearEventoDatabaseHelper crearEventoDatabaseHelper;
    private CrearEventos crearEventos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_evento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        initObjects();
        initListeners();
    }

    //Initialize Views
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEvento = (TextInputLayout) findViewById(R.id.textInputLayoutEvento);
        textInputLayoutNombre = (TextInputLayout) findViewById(R.id.textInputLayoutNombre_Evento);
        textInputLayoutCategoria = (TextInputLayout) findViewById(R.id.textInputLayoutCategoria);
        textInputLayoutCiudad = (TextInputLayout) findViewById(R.id.textInputLayoutCiudad);

        textInputEditTextEvento = (TextInputEditText) findViewById(R.id.textInputEditTextEvento);
        textInputEditTextNombre = (TextInputEditText) findViewById(R.id.textInputEditTextNombre_Evento);
        textInputEditTextCategoria = (TextInputEditText) findViewById(R.id.textInputEditTextCategoria);
        textInputEditTextCiudad = (TextInputEditText) findViewById(R.id.textInputEditTextCiudad);

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
        inputValidationCrearEvento = new InputValidationCrearEvento(activity);
        crearEventoDatabaseHelper = new CrearEventoDatabaseHelper(activity);
        crearEventos = new CrearEventos();
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
        if (!inputValidationCrearEvento.isInputEditTextFilled(textInputEditTextEvento, textInputLayoutEvento, getString(R.string.error_message_evento))) {
            return;
        }
        if (!inputValidationCrearEvento.isInputEditTextFilled(textInputEditTextCategoria, textInputLayoutCategoria, getString(R.string.error_message_categoria))) {
            return;
        }
        if (!inputValidationCrearEvento.isInputEditTextFilled(textInputEditTextNombre, textInputLayoutNombre, getString(R.string.error_message_nombre_evento))) {
            return;
        }
        if (!inputValidationCrearEvento.isInputEditTextFilled(textInputEditTextCiudad, textInputLayoutCiudad, getString(R.string.error_message_ciudad))) {
            return;
        }
        if (!crearEventoDatabaseHelper.checkUser(textInputEditTextEvento.getText().toString().trim())) {

            crearEventos.setEvento(textInputEditTextEvento.getText().toString().trim());
            crearEventos.setNombre(textInputEditTextNombre.getText().toString().trim());
            crearEventos.setCiudad(textInputEditTextCiudad.getText().toString().trim());
            crearEventos.setCategoria(textInputEditTextCategoria.getText().toString().trim());


            crearEventoDatabaseHelper.addEventos(crearEventos);

            // Snack Bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(activity, Ingresar_ListEvento.class);
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
        textInputEditTextEvento.setText(null);
        textInputEditTextCategoria.setText(null);
        textInputEditTextNombre.setText(null);
        textInputEditTextCiudad.setText(null);
    }
}