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
import com.bandmusic.bandmusic.ayudas.InputValidationJuradosEvento;
import com.bandmusic.bandmusic.modelos.JuradosEvento;

import com.bandmusic.bandmusic.base_datos_SQL.JuradosEventoDatabaseHelper;


public class Jurados_Evento extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = Jurados_Evento.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutJuradosEvento;
    private TextInputLayout textInputLayoutJuradosCategoria;
    private TextInputLayout textInputLayoutJuradosNombre_Evento;
    private TextInputLayout textInputLayoutJuradosNumero_Jurados;


    private TextInputEditText textInputEditTextJuradosEvento;
    private TextInputEditText textInputEditTextJuradosCategoria;
    private TextInputEditText textInputEditTextJuradosNombre_Evento;
    private TextInputEditText textInputEditTextJuradosNumero_Jurados;


    private AppCompatButton appCompatButtonRegister;

    private InputValidationJuradosEvento inputValidationJuradosEvento;
    private JuradosEventoDatabaseHelper juradosEventoDatabaseHelper;
    private JuradosEvento juradosEvento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurados__evento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        initObjects();
        initListeners();
    }

    //Initialize Views
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutJuradosEvento = (TextInputLayout) findViewById(R.id.textInputLayoutJuradosEvento);
        textInputLayoutJuradosCategoria = (TextInputLayout) findViewById(R.id.textInputLayoutJuradosCategoria);
        textInputLayoutJuradosNombre_Evento = (TextInputLayout) findViewById(R.id.textInputLayoutJuradosNombre_Evento);
        textInputLayoutJuradosNumero_Jurados = (TextInputLayout) findViewById(R.id.textInputLayoutJuradosNumero_Jurados);

        textInputEditTextJuradosEvento = (TextInputEditText) findViewById(R.id.textInputEditTextJuradosEvento);
        textInputEditTextJuradosCategoria = (TextInputEditText) findViewById(R.id.textInputEditTextJuradosCategoria);
        textInputEditTextJuradosNombre_Evento = (TextInputEditText) findViewById(R.id.textInputEditTextJuradosNombre_Evento);
        textInputEditTextJuradosNumero_Jurados = (TextInputEditText) findViewById(R.id.textInputEditTextJuradosNumero_Jurados);

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
        inputValidationJuradosEvento = new InputValidationJuradosEvento(activity);
        juradosEventoDatabaseHelper = new JuradosEventoDatabaseHelper(activity);
        juradosEvento = new JuradosEvento();
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
        if (!inputValidationJuradosEvento.isInputEditTextFilled(textInputEditTextJuradosEvento, textInputLayoutJuradosEvento, getString(R.string.error_message_evento))) {
            return;
        }
        if (!inputValidationJuradosEvento.isInputEditTextFilled(textInputEditTextJuradosCategoria, textInputLayoutJuradosCategoria, getString(R.string.error_message_categoria))) {
            return;
        }
        if (!inputValidationJuradosEvento.isInputEditTextFilled(textInputEditTextJuradosNombre_Evento, textInputLayoutJuradosNombre_Evento, getString(R.string.error_message_nombre_evento))) {
            return;
        }
        if (!inputValidationJuradosEvento.isInputEditTextFilled(textInputEditTextJuradosNumero_Jurados, textInputLayoutJuradosNumero_Jurados, getString(R.string.error_message_numjurados))) {
            return;
        }
        if (!juradosEventoDatabaseHelper.checkUser(textInputEditTextJuradosEvento.getText().toString().trim())) {

            juradosEvento.setEvento(textInputEditTextJuradosEvento.getText().toString().trim());
            juradosEvento.setCategoria(textInputEditTextJuradosCategoria.getText().toString().trim());
            juradosEvento.setNombre(textInputEditTextJuradosNombre_Evento.getText().toString().trim());
            juradosEvento.setNum(textInputEditTextJuradosNumero_Jurados.getText().toString().trim());


            juradosEventoDatabaseHelper.addJurados(juradosEvento);

            // Snack Bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(activity, Jurados_ListEvento.class);
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
        textInputEditTextJuradosEvento.setText(null);
        textInputEditTextJuradosCategoria.setText(null);
        textInputEditTextJuradosNombre_Evento.setText(null);
        textInputEditTextJuradosCategoria.setText(null);
    }
}
