package com.example.parcial_ll_yoel_urquijo_barroso_150739.db;

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
import com.example.parcial_ll_yoel_urquijo_barroso_150739.DetailActivity;
import com.example.parcial_ll_yoel_urquijo_barroso_150739.R;

import java.util.List;

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.PeliculaViewHolder> {

    private List<Peliculas> listaPeliculas;
    private Context context;
    private boolean isGrid;

    public PeliculasAdapter(List<Peliculas> listaPeliculas, Context context, boolean isGrid) {
        this.listaPeliculas = listaPeliculas;
        this.context = context;
        this.isGrid = isGrid;
    }

    public void setGrid(boolean isGrid) {
        this.isGrid = isGrid;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return isGrid ? 1 : 0;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = (viewType == 1) ? R.layout.item_movie_grid : R.layout.item_movie_linear;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Peliculas pelicula = listaPeliculas.get(position);

        Glide.with(context).load(pelicula.imagenUrl).placeholder(R.mipmap.ic_launcher).into(holder.imgPelicula);

        if (!isGrid && holder.txtTitulo != null) {
            holder.txtTitulo.setText(pelicula.titulo);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("pelicula_data", pelicula);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPelicula;
        TextView txtTitulo;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPelicula = itemView.findViewById(R.id.imgMovie);
            txtTitulo = itemView.findViewById(R.id.txtTitle);
        }
    }
}