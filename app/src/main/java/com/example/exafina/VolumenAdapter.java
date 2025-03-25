package com.example.exafina;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VolumenAdapter extends RecyclerView.Adapter<VolumenAdapter.ViewHolder> {

    public interface OnVolumenClickListener {
        void onVolumenClick(Volumen volumen);
    }

    private List<Volumen> lista;
    private OnVolumenClickListener listener;

    public VolumenAdapter(List<Volumen> lista, OnVolumenClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPortadaVolumen;
        TextView txtTitulo, txtVolumen, txtNumero, txtAnio, txtFecha, txtDoi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPortadaVolumen = itemView.findViewById(R.id.imgPortadaVolumen);
            txtTitulo = itemView.findViewById(R.id.txtTituloVol);
            txtVolumen = itemView.findViewById(R.id.txtVolumen);
            txtNumero = itemView.findViewById(R.id.txtNumero);
            txtAnio = itemView.findViewById(R.id.txtAnio);
            txtFecha = itemView.findViewById(R.id.txtFechaPub);
            txtDoi = itemView.findViewById(R.id.txtDoi);
        }

        public void bind(Volumen vol, OnVolumenClickListener listener) {
            itemView.setOnClickListener(v -> listener.onVolumenClick(vol));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_volumen, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Volumen vol = lista.get(position);

        holder.txtTitulo.setText(vol.getTitulo());
        holder.txtVolumen.setText("Volumen: " + vol.getVolumen());
        holder.txtNumero.setText("Número: " + vol.getNumero());
        holder.txtAnio.setText("Año: " + vol.getAnio());
        holder.txtFecha.setText("Publicado: " + vol.getFechaPublicacion());
        holder.txtDoi.setText("DOI: " + vol.getDoi());

        Glide.with(holder.itemView.getContext())
                .load(vol.getPortada())
                .placeholder(R.drawable.img)
                .into(holder.imgPortadaVolumen);

        // 👉 Al hacer clic en el volumen, abre la lista de artículos
        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, ArticuloActivity.class);
            intent.putExtra("issue_id", vol.getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
