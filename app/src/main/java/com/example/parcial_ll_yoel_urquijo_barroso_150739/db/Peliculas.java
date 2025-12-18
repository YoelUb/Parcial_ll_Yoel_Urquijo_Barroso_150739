package com.example.parcial_ll_yoel_urquijo_barroso_150739.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "peliculas")
public class Peliculas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public String descripcion;
    public String imagenUrl;

    public String actores;

    public Peliculas(String titulo, String descripcion, String imagenUrl, String actores) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.actores = actores;
    }
}