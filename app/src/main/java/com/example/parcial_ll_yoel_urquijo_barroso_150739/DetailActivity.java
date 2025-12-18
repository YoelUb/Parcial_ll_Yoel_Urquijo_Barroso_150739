package com.example.parcial_ll_yoel_urquijo_barroso_150739;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.parcial_ll_yoel_urquijo_barroso_150739.db.Peliculas;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detalle de Película");
        }

        Peliculas pelicula = (Peliculas) getIntent().getSerializableExtra("pelicula_data");
        ImageView img = findViewById(R.id.imgDetail);
        TextView titulo = findViewById(R.id.txtDetailTitle);
        TextView descripcion = findViewById(R.id.txtDetailDesc);

        if (pelicula != null) {
            titulo.setText(pelicula.titulo);
            descripcion.setText(pelicula.descripcion);
            Glide.with(this).load(pelicula.imagenUrl).placeholder(R.mipmap.ic_launcher).into(img);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}