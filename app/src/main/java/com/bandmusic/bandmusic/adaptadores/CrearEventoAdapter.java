package com.bandmusic.bandmusic.adaptadores;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.modelos.CrearEventos;

import java.util.ArrayList;

public class CrearEventoAdapter extends RecyclerView.Adapter<CrearEventoAdapter.EventosViewHolder>  {

    private ArrayList<CrearEventos> listEventos;
    public ImageView overflow;
    private Context mContext;


    public CrearEventoAdapter(ArrayList<CrearEventos> listEventos, Context mContext) {
        this.listEventos = listEventos;
        this.mContext = mContext;

    }

    public class EventosViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewEvento;
        public AppCompatTextView textViewCategoria;
        public AppCompatTextView textViewNombre;
        public AppCompatTextView textViewCiudad;
        public  ImageView overflow;

        public EventosViewHolder(View view) {
            super(view);
            textViewEvento = (AppCompatTextView) view.findViewById(R.id.textViewEvento);
            textViewNombre = (AppCompatTextView) view.findViewById(R.id.textViewNombre);
            textViewCategoria = (AppCompatTextView) view.findViewById(R.id.textViewCategoria);
            textViewCiudad = (AppCompatTextView) view.findViewById(R.id.textViewCiudad);

        }


    }

    @Override
    public EventosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingresar_evento, parent, false);

        return new EventosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventosViewHolder holder, int position) {
        holder.textViewEvento.setText(listEventos.get(position).getEvento());
        holder.textViewCategoria.setText(listEventos.get(position).getCategoria());
        holder.textViewNombre.setText(listEventos.get(position).getNombre());
        holder.textViewCiudad.setText(listEventos.get(position).getCiudad());

    }

    @Override
    public int getItemCount() {
        return listEventos.size();
    }

    public void setFilter(ArrayList<CrearEventos> newList){
        listEventos = new ArrayList<>();
        listEventos.addAll(newList);
        notifyDataSetChanged();
    }
}
