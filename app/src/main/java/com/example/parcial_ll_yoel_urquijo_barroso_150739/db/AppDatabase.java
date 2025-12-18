package com.example.parcial_ll_yoel_urquijo_barroso_150739.db;

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
                                                new Peliculas(
                                                        "El Caballero Oscuro",
                                                        "Director: Christopher Nolan",
                                                        "https://almacen-rmr.tionazo.com/pelis/caballero-oscuro.jpg"
                                                ),
                                                new Peliculas(
                                                        "Cadena Perpetua",
                                                        "Director: Frank Darabont",
                                                        "https://almacen-rmr.tionazo.com/pelis/cadena-perpetua.jpg"
                                                ),
                                                new Peliculas(
                                                        "City Lights",
                                                        "Director: Charlie Chaplin",
                                                        "https://almacen-rmr.tionazo.com/pelis/city_lights.jpg"
                                                )
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