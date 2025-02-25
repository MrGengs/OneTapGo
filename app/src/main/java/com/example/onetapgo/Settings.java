package com.example.onetapgo;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {

    private Switch darkModeSwitch;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inisialisasi Toolbar
        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish()); // Tombol kembali

        // Inisialisasi View
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        // SharedPreferences untuk menyimpan pengaturan
        sharedPreferences = getSharedPreferences("SettingsPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Memuat status dark mode dari SharedPreferences
        boolean isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false);
        darkModeSwitch.setChecked(isDarkMode);

        // Simpan perubahan secara otomatis saat switch diubah
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("DARK_MODE", isChecked);
            editor.apply();
        });
    }
}