package com.example.parcial_ll_yoel_urquijo_barroso_150739;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rbGrid, rbLinear;
    private Switch switchActors;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Ajustes");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        radioGroup = findViewById(R.id.radioGroupViews);
        rbGrid = findViewById(R.id.rbGrid);
        rbLinear = findViewById(R.id.rbLinear);
        switchActors = findViewById(R.id.switchActors);

        prefs = getSharedPreferences("app_settings", Context.MODE_PRIVATE);

        boolean isGrid = prefs.getBoolean("is_grid", false);
        if (isGrid) rbGrid.setChecked(true); else rbLinear.setChecked(true);

        boolean showActors = prefs.getBoolean("show_actors", false);
        switchActors.setChecked(showActors);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            boolean newModeIsGrid = (checkedId == R.id.rbGrid);
            prefs.edit().putBoolean("is_grid", newModeIsGrid).apply();
        });

        switchActors.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("show_actors", isChecked).apply();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}