package com.example.onetapgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;
    private MaterialCardView mapsCard, contactsCard, calculatorCard;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi View
        mapsCard = findViewById(R.id.mapsCard);
        contactsCard = findViewById(R.id.contactsCard);
        calculatorCard = findViewById(R.id.calculatorCard);
        bottomNavigation = findViewById(R.id.bottomNavigation);

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
                Toast.makeText(MainActivity.this, "Settings selected", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }
}
