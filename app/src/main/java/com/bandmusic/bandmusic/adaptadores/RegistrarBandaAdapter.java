package com.bandmusic.bandmusic.adaptadores;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.modelos.Bandas;

import java.util.ArrayList;

public class RegistrarBandaAdapter extends RecyclerView.Adapter<RegistrarBandaAdapter.BandasViewHolder>  {

    private ArrayList<Bandas> listBandas;
    public ImageView overflow;
    private Context mContext;


    public RegistrarBandaAdapter(ArrayList<Bandas> listBandas, Context mContext) {
        this.listBandas = listBandas;
        this.mContext = mContext;

    }

    public class BandasViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewNombre_Banda;
        public AppCompatTextView textViewOrganizacion;
        public AppCompatTextView textViewCiudad;
        public AppCompatTextView textViewCategoria;
        public  ImageView overflow;


        public BandasViewHolder(View view) {
            super(view);
            textViewNombre_Banda = (AppCompatTextView) view.findViewById(R.id.textViewNombre_Banda);
            textViewOrganizacion = (AppCompatTextView) view.findViewById(R.id.textViewOrganizacion);
            textViewCiudad = (AppCompatTextView) view.findViewById(R.id.textViewCiudad);
            textViewCategoria = (AppCompatTextView) view.findViewById(R.id.textViewCategoria);
        }


    }

    @Override
    public BandasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inscribir_bandas, parent, false);

        return new BandasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BandasViewHolder holder, int position) {
        holder.textViewNombre_Banda.setText(listBandas.get(position).getNombre());
        holder.textViewOrganizacion.setText(listBandas.get(position).getOrganizacion());
        holder.textViewCiudad.setText(listBandas.get(position).getCiudad());
        holder.textViewCategoria.setText(listBandas.get(position).getCategoria());

    }

    @Override
    public int getItemCount() {
        return listBandas.size();
    }

    public void setFilter(ArrayList<Bandas> newList){
        listBandas = new ArrayList<>();
        listBandas.addAll(newList);
        notifyDataSetChanged();
    }
}
