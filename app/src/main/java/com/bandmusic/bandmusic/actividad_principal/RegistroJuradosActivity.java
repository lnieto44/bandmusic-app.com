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
import com.bandmusic.bandmusic.ayudas.InputValidationJurados;
import com.bandmusic.bandmusic.modelos.Jurados;

import com.bandmusic.bandmusic.base_datos_SQL.JuradosDatabaseHelper;

public class RegistroJuradosActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegistroJuradosActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutJuradosNombre;
    private TextInputLayout textInputLayoutJuradosCorreo;
    private TextInputLayout textInputLayoutJuradosClave;
    private TextInputLayout textInputLayoutJuradosCategoria;


    private TextInputEditText textInputEditTextJuradosNombre;
    private TextInputEditText textInputEditTextJuradosCorreo;
    private TextInputEditText textInputEditTextJuradosClave;
    private TextInputEditText textInputEditTextJuradosCategoria;


    private AppCompatButton appCompatButtonRegister;

    private InputValidationJurados inputValidation;
    private JuradosDatabaseHelper juradosDatabaseHelper;
    private Jurados jurados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_jurados);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        initObjects();
        initListeners();
    }

    //Initialize Views
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutJuradosNombre = (TextInputLayout) findViewById(R.id.textInputLayoutJuradosNombre);
        textInputLayoutJuradosCorreo = (TextInputLayout) findViewById(R.id.textInputLayoutJuradosCorreo);
        textInputLayoutJuradosClave = (TextInputLayout) findViewById(R.id.textInputLayoutJuradosClave);
        textInputLayoutJuradosCategoria = (TextInputLayout) findViewById(R.id.textInputLayoutJuradosCategoria);

        textInputEditTextJuradosNombre = (TextInputEditText) findViewById(R.id.textInputEditTextJuradosNombre);
        textInputEditTextJuradosCorreo = (TextInputEditText) findViewById(R.id.textInputEditTextJuradosCorreo);
        textInputEditTextJuradosClave = (TextInputEditText) findViewById(R.id.textInputEditTextJuradosClave);
        textInputEditTextJuradosCategoria = (TextInputEditText) findViewById(R.id.textInputEditTextJuradosCategoria);

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
        inputValidation = new InputValidationJurados(activity);
        juradosDatabaseHelper = new JuradosDatabaseHelper(activity);
        jurados = new Jurados();
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
        if (!inputValidation.isInputEditTextFilled(textInputEditTextJuradosNombre, textInputLayoutJuradosNombre, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextJuradosCorreo, textInputLayoutJuradosCorreo, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextJuradosCorreo, textInputLayoutJuradosCorreo, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextJuradosClave, textInputLayoutJuradosClave, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextJuradosCategoria, textInputLayoutJuradosCategoria, getString(R.string.error_message_email))) {
            return;
        }

        if (!juradosDatabaseHelper.checkUser(textInputEditTextJuradosCorreo.getText().toString().trim(),
                textInputEditTextJuradosCorreo.getText().toString().trim())) {

            jurados.setNombre(textInputEditTextJuradosNombre.getText().toString().trim());
            jurados.setCorreo(textInputEditTextJuradosCorreo.getText().toString().trim());
            jurados.setClave(textInputEditTextJuradosClave.getText().toString().trim());
            jurados.setCategoria(textInputEditTextJuradosCategoria.getText().toString().trim());


            juradosDatabaseHelper.addJurados(jurados);

            // Snack Bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(activity, Jurados_ListActivity.class);
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
        textInputEditTextJuradosNombre.setText(null);
        textInputEditTextJuradosCorreo.setText(null);
        textInputEditTextJuradosClave.setText(null);
        textInputEditTextJuradosCategoria.setText(null);
    }
}