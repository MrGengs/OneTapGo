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

    private MaterialToolbar topAppBar;
    private EditText searchEditText;
    private MaterialCardView mapsCard, contactsCard, calculatorCard;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi View
        topAppBar = findViewById(R.id.topAppBar);
        searchEditText = findViewById(R.id.searchEditText);
        mapsCard = findViewById(R.id.mapsCard);
        contactsCard = findViewById(R.id.contactsCard);
        calculatorCard = findViewById(R.id.calculatorCard);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Pengaturan Toolbar
        setSupportActionBar(topAppBar);

        // Event Listener untuk Kartu
        mapsCard.setOnClickListener(v -> {
            Toast.makeText(this, "Maps clicked", Toast.LENGTH_SHORT).show();
        });

        contactsCard.setOnClickListener(v -> {
            Toast.makeText(this, "Contacts clicked", Toast.LENGTH_SHORT).show();
        });

        calculatorCard.setOnClickListener(v -> {
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
        });

        // Pengaturan Bottom Navigation
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.favorites:
                    Toast.makeText(MainActivity.this, "Favorites selected", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.profile:
                    Toast.makeText(MainActivity.this, "Profile selected", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
    }

    // Menampilkan menu di toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);  // Menambahkan menu dari XML
        return true;
    }

    // Menangani klik item menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
