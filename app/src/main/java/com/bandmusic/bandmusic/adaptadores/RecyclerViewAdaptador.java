package com.bandmusic.bandmusic.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.modelos.Sobre_Nosotros;

import java.util.ArrayList;
import java.util.List;



public class RecyclerViewAdaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Sobre_Nosotros> items;

    public RecyclerViewAdaptador(Activity activity, ArrayList data) {
        this.context = activity;
        this.items = data;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nosotros, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
/*
        Typeface tf_thin = Typeface.createFromAsset(
                context.getAssets(), "fonts/roboto_thin.ttf");

        viewHolder.itemNombre.setTypeface(tf_thin);
        viewHolder.itemTipo.setTypeface(tf_thin);
*/
        Sobre_Nosotros currentItem = (Sobre_Nosotros) getItem(position);
        viewHolder.itemNombre.setText(currentItem.getNombre());
        viewHolder.itemProfesion.setText(currentItem.getProfesion());
        viewHolder.itemCorreo.setText(currentItem.getCorreo());
        viewHolder.itemCelular.setText(currentItem.getCelular());
        viewHolder.itemImagen.setImageResource(currentItem.getImagen());

        return convertView;
    }

    private class ViewHolder {
        TextView itemNombre; TextView itemCorreo;
        TextView itemProfesion; TextView itemCelular;
        ImageView itemImagen;

        public ViewHolder(View view) {
            itemNombre = (TextView)view.findViewById(R.id.nombre);
            itemProfesion = (TextView) view.findViewById(R.id.profesion);
            itemCorreo = (TextView) view.findViewById(R.id.correo);
            itemCelular = (TextView) view.findViewById(R.id.celular);
            itemImagen = (ImageView) view.findViewById(R.id.ivImagen);
        }
    }

}
