package com.bandmusic.bandmusic.adaptadores;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bandmusic.bandmusic.R;
import com.bandmusic.bandmusic.modelos.Jurados;

import java.util.ArrayList;

public class JuradosRecyclerAdapter extends RecyclerView.Adapter<JuradosRecyclerAdapter.JuradosViewHolder>  {

    private ArrayList<Jurados> listJurados;
    public ImageView overflow;
    private Context mContext;


    public JuradosRecyclerAdapter(ArrayList<Jurados> listJurados, Context mContext) {
        this.listJurados = listJurados;
        this.mContext = mContext;

    }

    public class JuradosViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewNombre;
        public AppCompatTextView textViewCorreo;
        public AppCompatTextView textViewClave;
        public AppCompatTextView textViewCategoria;
        public  ImageView overflow;

        public JuradosViewHolder(View view) {
            super(view);
            textViewNombre = (AppCompatTextView) view.findViewById(R.id.textViewNombre);
            textViewCorreo = (AppCompatTextView) view.findViewById(R.id.textViewCorreo);
            textViewClave = (AppCompatTextView) view.findViewById(R.id.textViewClave);
            textViewCategoria = (AppCompatTextView) view.findViewById(R.id.textViewCategoria);

        }


    }

    @Override
    public JuradosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jurados, parent, false);

        return new JuradosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final JuradosViewHolder holder, int position) {
        holder.textViewNombre.setText(listJurados.get(position).getNombre());
        holder.textViewCorreo.setText(listJurados.get(position).getCorreo());
        holder.textViewClave.setText(listJurados.get(position).getClave());
        holder.textViewCategoria.setText(listJurados.get(position).getCategoria());


    }

    @Override
    public int getItemCount() {
        return listJurados.size();
    }

    public void setFilter(ArrayList<Jurados> newList){
        listJurados = new ArrayList<>();
        listJurados.addAll(newList);
        notifyDataSetChanged();
    }
}
