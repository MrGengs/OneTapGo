package com.example.onetapgo;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Settings extends AppCompatActivity {
    private Switch darkModeSwitch;
    private ImageView btnBack;
    private LinearLayout rootLayout;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "app_settings";
    private static final String DARK_MODE = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inisialisasi komponen UI
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        btnBack = findViewById(R.id.btnBack);
        rootLayout = findViewById(R.id.root_layout);

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean(DARK_MODE, false);

        // Set status switch sesuai dengan pengaturan
        darkModeSwitch.setChecked(isDarkMode);

        // Terapkan warna background
        updateBackground(isDarkMode);

        // Tambahkan listener untuk switch dark mode
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(DARK_MODE, isChecked);
            editor.apply();

            // Terapkan perubahan tema
            AppCompatDelegate.setDefaultNightMode(isChecked ?
                    AppCompatDelegate.MODE_NIGHT_YES :
                    AppCompatDelegate.MODE_NIGHT_NO);

            // Update background sesuai mode
            updateBackground(isChecked);

            // Restart MainActivity agar perubahan langsung terlihat
            restartMainActivity();
        });

        // Listener untuk tombol kembali
        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void updateBackground(boolean isDarkMode) {
        if (rootLayout != null) {
            rootLayout.setBackgroundColor(getResources().getColor(
                    isDarkMode ? android.R.color.black : android.R.color.white
            ));
        }
    }

    private void restartMainActivity() {
        Intent intent = new Intent(Settings.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}