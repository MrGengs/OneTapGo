package com.example.onetapgo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.net.Uri;
import android.content.Intent;
import android.widget.TextView;

public class InfoAplikasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_aplikasi);

        // Set informasi aplikasi
        TextView appName = findViewById(R.id.app_name);
        TextView appVersion = findViewById(R.id.app_version);
        TextView appDeveloper = findViewById(R.id.app_developer);
        TextView appWebsite = findViewById(R.id.app_website);

        appName.setText("Nama Aplikasi: One Tap Go");
        appVersion.setText("Versi: 1.0.0");
        appDeveloper.setText("Developer: Mr. Gengs");
        appWebsite.setText("Website: MrGengs.com");

        // Membuat teks website bisa diklik dan membuka browser
        appWebsite.setOnClickListener(view -> {
            String url = "https://bit.ly/MrGengs";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}