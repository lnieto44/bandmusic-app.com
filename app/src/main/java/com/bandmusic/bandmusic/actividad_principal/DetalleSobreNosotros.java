package com.bandmusic.bandmusic.actividad_principal;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.modelos.Sobre_Nosotros;

public class DetalleSobreNosotros extends AppCompatActivity {
    private ImageView ivImagen;
    private TextView tvNombre, Profesion, Correo, Celular;

   // private Typeface tf_thing;
    //private Typeface tf_bold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_sobre_nosotros);
        ivImagen = (ImageView) findViewById(R.id.ivDetalleImagen);
        tvNombre = (TextView) findViewById(R.id.tvDetalleNombre);
        Profesion = (TextView) findViewById(R.id.DetalleProfesion);
        Correo = (TextView) findViewById(R.id.DetalleCorreo);
        Celular = (TextView) findViewById(R.id.DetalleCelular);
/*
        tf_thing = Typeface.createFromAsset(
                getAssets(), "fonts/roboto_thin.ttf");
        tf_bold = Typeface.createFromAsset(
                getAssets(), "fonts/roboto_black.ttf");

        tvNombre.setTypeface(tf_bold);
        tvHAbilidades.setTypeface(tf_thing);
*/
        Sobre_Nosotros nosotros = (Sobre_Nosotros) getIntent().getSerializableExtra("nosotros");
        tvNombre.setText(nosotros.getNombre().toString());
        Profesion.setText(nosotros.getProfesion().toString());
        Correo.setText(nosotros.getCorreo().toString());
        Celular.setText(nosotros.getCelular().toString());
        ivImagen.setImageResource(nosotros.getImagen());
    }

}
