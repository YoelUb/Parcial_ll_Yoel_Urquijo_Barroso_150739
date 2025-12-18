package com.example.parcial_ll_yoel_urquijo_barroso_150739.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PeliculasDao {
    @Query("SELECT * FROM peliculas")
    List<Peliculas> getAll();

    @Insert
    void insertAll(Peliculas... peliculas);

    @Query("DELETE FROM peliculas")
    void deleteAll();
}