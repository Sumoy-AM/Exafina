package com.example.exafina;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RevistaAdapter extends RecyclerView.Adapter<RevistaAdapter.ViewHolder> {

    private final List<Revista> listaRevistas;
    private final OnItemClickListener listener;

    // Interfaz personalizada
    public interface OnItemClickListener {
        void onItemClick(Revista revista);
    }

    public RevistaAdapter(List<Revista> listaRevistas, OnItemClickListener listener) {
        this.listaRevistas = listaRevistas;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPortada;
        TextView txtNombre, txtDescripcion, txtAbreviacion;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPortada = itemView.findViewById(R.id.imgPortada);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtAbreviacion = itemView.findViewById(R.id.txtAbreviacion);
        }

        public void bind(final Revista revista, final OnItemClickListener listener) {
            imgPortada.setOnClickListener(v -> listener.onItemClick(revista));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_revista, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Revista revista = listaRevistas.get(position);

        holder.txtNombre.setText(revista.getNombre());
        holder.txtDescripcion.setText(revista.getDescripcion());
        holder.txtAbreviacion.setText(revista.getAbbreviation());

        Glide.with(holder.itemView.getContext())
                .load(revista.getPortada())
                .placeholder(R.drawable.img)
                .into(holder.imgPortada);

        holder.bind(revista, listener);
    }

    @Override
    public int getItemCount() {
        return listaRevistas.size();
    }
}
