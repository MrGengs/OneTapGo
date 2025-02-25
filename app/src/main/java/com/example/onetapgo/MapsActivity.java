package com.example.onetapgo;

import android.os.Bundle;
import android.net.Uri;
import androidx.activity.EdgeToEdge;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MapsActivity extends AppCompatActivity {
    private EditText editTextAddress;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps); // Pastikan sesuai dengan XML layout yang benar

        // Inisialisasi UI
        editTextAddress = findViewById(R.id.editTextAddress);
        Button btnOpenMaps = findViewById(R.id.btnOpenMaps);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // Aksi klik tombol untuk membuka Google Maps
        btnOpenMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMaps();
            }
        });
    }

    // Fungsi untuk membuka Google Maps dengan alamat yang dimasukkan
    private void openGoogleMaps() {
        String address = editTextAddress.getText().toString();
        if (!address.isEmpty()) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        } else {
            editTextAddress.setError("Masukkan alamat terlebih dahulu!");
        }
    }
}