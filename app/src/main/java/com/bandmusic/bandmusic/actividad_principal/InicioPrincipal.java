package com.bandmusic.bandmusic.actividad_principal;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.BlockedNumberContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bandmusic.bandmusic.Paneles_Laterales.MainActivityAdmin;
import com.bandmusic.bandmusic.Paneles_Laterales.Registrar_Banda;
import com.bandmusic.bandmusic.R;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class InicioPrincipal extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3;
    //private Recipe recipe;

    @Override public void onStart() {
        super.onStart();
        Branch branch = Branch.getInstance(getApplicationContext());
        branch.initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null){
                    Log.i("BranchConfigTest","deep link data: " + referringParams);
                }
            }
        },this.getIntent().getData(), this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Branch.getInstance(getApplicationContext()).closeSession();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_principal);

        btn1 = (Button) findViewById(R.id.button3);
        btn2 = (Button) findViewById(R.id.button4);
        btn3 = (Button) findViewById(R.id.button5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        handleIntent(getIntent());
    }


    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }



    private void handleIntent(Intent intent) {
        String appLinkAction = intent.getAction();
        Uri appLinkData = intent.getData();
        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null){
            String recipeId = appLinkData.getLastPathSegment();
            Uri inicioprincipal = Uri.parse("content://com.bandmusic-app/inicioprincipal/").buildUpon()
                    .appendPath(recipeId).build();
            showRecipe(inicioprincipal);
        }
    }

    private void showRecipe(Uri recipeUri) {
    }



    @Override
    public void onClick(View v) {

        Intent explicit_intent;//Declaro el Intent

        switch (v.getId()){//Obtengo el ID del boton precionado

            case R.id.button3://Si el ID es btn

                //Instanciamos el Intent dandole:
                // el contexto y la clase a la cual nos deseamos dirigir
                explicit_intent = new Intent(this,LoginActivity.class);

                //lo iniciamos pasandole la intencion
                startActivity(explicit_intent);
                break;

            case R.id.button4://Si el ID es btn
                explicit_intent = new Intent(this, InvitadoInicio.class);
                startActivity(explicit_intent);
                break;

            case R.id.button5://Si el ID es btn
                explicit_intent = new Intent(this, Nosotros.class);
                startActivity(explicit_intent);
                break;

        }
    }


}
