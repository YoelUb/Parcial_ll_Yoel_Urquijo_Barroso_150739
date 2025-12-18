package com.example.parcial_ll_yoel_urquijo_barroso_150739;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parcial_ll_yoel_urquijo_barroso_150739.db.AppDatabase;
import com.example.parcial_ll_yoel_urquijo_barroso_150739.db.Peliculas;
import com.example.parcial_ll_yoel_urquijo_barroso_150739.db.PeliculasAdapter;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PeliculasAdapter adapter;
    private AppDatabase db;
    private SharedPreferences prefs;
    private boolean isGridMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        isGridMode = prefs.getBoolean("is_grid", false);

        db = AppDatabase.getInstance(this);
        List<Peliculas> lista = db.peliculasDao().getAll();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PeliculasAdapter(lista, this, isGridMode);
        recyclerView.setAdapter(adapter);

        updateLayoutManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean savedMode = prefs.getBoolean("is_grid", false);
        if (isGridMode != savedMode) {
            isGridMode = savedMode;
            adapter.setGrid(isGridMode);
            updateLayoutManager();
        }
    }

    private void updateLayoutManager() {
        if (isGridMode) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}