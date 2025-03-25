package com.example.exafina;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ViewHolder> {

    private List<Articulo> listaArticulos;

    public ArticuloAdapter(List<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtResumen, txtAutores;
        Button btnPdf, btnHtml;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloArticulo);
            txtResumen = itemView.findViewById(R.id.txtResumenArticulo);
            txtAutores = itemView.findViewById(R.id.txtAutores);
            btnPdf = itemView.findViewById(R.id.btnPdf);
            btnHtml = itemView.findViewById(R.id.btnHtml);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_articulo, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articulo art = listaArticulos.get(position);
        holder.txtTitulo.setText(art.getTitulo());
        holder.txtResumen.setText(android.text.Html.fromHtml(art.getResumen()));
        holder.txtAutores.setText("Autores: " + String.join(", ", art.getAutores()));

        holder.btnPdf.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(art.getPdfUrl()));
            v.getContext().startActivity(intent);
        });

        holder.btnHtml.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(art.getHtmlUrl()));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }
}
