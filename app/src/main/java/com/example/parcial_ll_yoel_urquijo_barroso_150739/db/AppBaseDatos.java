package com.example.parcial_ll_yoel_urquijo_barroso_150739.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;


public class AppBaseDatos{

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.Executors;

@Database(entities = {Peliculas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PeliculasDao peliculasDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "peliculas-db")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        getInstance(context).peliculasDao().insertAll(
                                                new Peliculas("Avatar", "Ciencia ficción en Pandora", "https://via.placeholder.com/150"),
                                                new Peliculas("Titanic", "Romance en alta mar", "https://via.placeholder.com/150"),
                                                new Peliculas("Inception", "Sueños dentro de sueños", "https://via.placeholder.com/150")
                                        );
                                    });
                                }
                            })
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
}
