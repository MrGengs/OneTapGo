package com.example.onetapgo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;
    private MaterialCardView mapsCard, contactsCard, calculatorCard;
    private BottomNavigationView bottomNavigation;
    private LinearLayout rootLayout;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "app_settings";
    private static final String DARK_MODE = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ambil status Dark Mode sebelum setContentView()
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean(DARK_MODE, false);

        // Terapkan tema
        applyTheme(isDarkMode);

        setContentView(R.layout.activity_main);

        // Inisialisasi View
        rootLayout = findViewById(R.id.root_layout);
        mapsCard = findViewById(R.id.mapsCard);
        contactsCard = findViewById(R.id.contactsCard);
        calculatorCard = findViewById(R.id.calculatorCard);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Terapkan warna latar belakang sesuai dengan mode
        updateBackground(isDarkMode);

        // Event Listener untuk Kartu
        mapsCard.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        });

        contactsCard.setOnClickListener(v -> {
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
        });

        calculatorCard.setOnClickListener(v -> {
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
        });

        // Pengaturan Bottom Navigation
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.settings) {
                // Buka SettingsActivity
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    // Metode untuk menerapkan tema
    private void applyTheme(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Metode untuk mengupdate background sesuai mode
    private void updateBackground(boolean isDarkMode) {
        if (rootLayout != null) {
            rootLayout.setBackgroundColor(getResources().getColor(
                    isDarkMode ? android.R.color.black : android.R.color.white
            ));
        }
    }
}
