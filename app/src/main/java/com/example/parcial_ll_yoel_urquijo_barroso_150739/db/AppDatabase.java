package com.example.parcial_ll_yoel_urquijo_barroso_150739.db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.Executors;

@Database(entities = {Peliculas.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PeliculasDao peliculasDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "peliculas-db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        getInstance(context).peliculasDao().insertAll(
                                                new Peliculas("El Caballero Oscuro", "Director: Christopher Nolan", "https://almacen-rmr.tionazo.com/pelis/caballero-oscuro.jpg", "Christian Bale, Heath Ledger, Aaron Eckhart"),
                                                new Peliculas("Cadena Perpetua", "Director: Frank Darabont", "https://almacen-rmr.tionazo.com/pelis/cadena-perpetua.jpg", "Tim Robbins, Morgan Freeman, William Sadler"),
                                                new Peliculas("City Lights", "Director: Charlie Chaplin", "https://almacen-rmr.tionazo.com/pelis/city_lights.jpg", "Charlie Chaplin, Virginia Cherrill, Florence Lee"),
                                                new Peliculas("Django", "Director: Quentin Tarantino", "https://almacen-rmr.tionazo.com/pelis/django.jpg", "Jamie Foxx, Christoph Waltz, Leonardo DiCaprio"),
                                                new Peliculas("Interestellar", "Director: Christopher Nolan", "https://almacen-rmr.tionazo.com/pelis/interestellar.jpg", "Matthew McConaughey, Anne Hathaway, Jessica Chastain"),
                                                new Peliculas("Malditos Bastardos", "Director: Quentin Tarantino", "https://almacen-rmr.tionazo.com/pelis/malditos-bastardos.jpg", "Brad Pitt, Christoph Waltz, Mélanie Laurent"),
                                                new Peliculas("El Padrino", "Director: Francis Ford Coppola", "https://almacen-rmr.tionazo.com/pelis/padrino.jpg", "Marlon Brando, Al Pacino, James Caan"),
                                                new Peliculas("Pulp Fiction", "Director: Quentin Tarantino", "https://almacen-rmr.tionazo.com/pelis/pulp_fiction.jpg", "John Travolta, Uma Thurman, Samuel L. Jackson"),
                                                new Peliculas("Reservoir Dogs", "Director: Quentin Tarantino", "https://almacen-rmr.tionazo.com/pelis/reservoir_dogs.jpg", "Harvey Keitel, Tim Roth, Michael Madsen"),
                                                new Peliculas("La Lista de Schindler", "Director: Steven Spielberg", "https://almacen-rmr.tionazo.com/pelis/schindler.jpg", "Liam Neeson, Ralph Fiennes, Ben Kingsley"),
                                                new Peliculas("Star Wars: Episode IV", "Director: George Lucas", "https://almacen-rmr.tionazo.com/pelis/starwars.jpg", "Mark Hamill, Harrison Ford, Carrie Fisher")
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