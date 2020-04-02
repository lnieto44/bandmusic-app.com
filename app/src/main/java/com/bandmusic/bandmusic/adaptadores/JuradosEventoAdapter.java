package com.bandmusic.bandmusic.adaptadores;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.modelos.JuradosEvento;

import java.util.ArrayList;

public class JuradosEventoAdapter extends RecyclerView.Adapter<JuradosEventoAdapter.JuradosViewHolder>  {

    private ArrayList<JuradosEvento> listJurados;
    public ImageView overflow;
    private Context mContext;


    public JuradosEventoAdapter(ArrayList<JuradosEvento> listJurados, Context mContext) {
        this.listJurados = listJurados;
        this.mContext = mContext;

    }

    public class JuradosViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewEvento;
        public AppCompatTextView textViewCategoria;
        public AppCompatTextView textViewNombre_Evento;
        public AppCompatTextView textViewNumero_Jurados;
        public  ImageView overflow;

        public JuradosViewHolder(View view) {
            super(view);
            textViewEvento = (AppCompatTextView) view.findViewById(R.id.textViewEvento);
            textViewCategoria = (AppCompatTextView) view.findViewById(R.id.textViewCategoria);
            textViewNombre_Evento = (AppCompatTextView) view.findViewById(R.id.textViewNombre_Evento);
            textViewNumero_Jurados = (AppCompatTextView) view.findViewById(R.id.textViewNumero_Jurados);

        }


    }

    @Override
    public JuradosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jurados_evento, parent, false);

        return new JuradosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final JuradosViewHolder holder, int position) {
        holder.textViewEvento.setText(listJurados.get(position).getEvento());
        holder.textViewCategoria.setText(listJurados.get(position).getCategoria());
        holder.textViewNombre_Evento.setText(listJurados.get(position).getNombre());
        holder.textViewNumero_Jurados.setText(listJurados.get(position).getNum());


    }

    @Override
    public int getItemCount() {
        return listJurados.size();
    }

    public void setFilter(ArrayList<JuradosEvento> newList){
        listJurados = new ArrayList<>();
        listJurados.addAll(newList);
        notifyDataSetChanged();
    }
}