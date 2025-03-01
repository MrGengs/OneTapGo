package com.example.onetapgo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

public class InfoAplikasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_aplikasi);

        // Set informasi aplikasi dari build.gradle
        TextView appName = findViewById(R.id.app_name);
        TextView appVersion = findViewById(R.id.app_version);
        TextView appDeveloper = findViewById(R.id.app_developer);
        TextView appWebsite = findViewById(R.id.app_website);

        appName.setText("Nama Aplikasi: One Tap Go");
        appVersion.setText("Versi: 1.0.0");
        appDeveloper.setText("Developer: Mr. Gengs");
        appWebsite.setText("Website: www.myapp.com");
    }
}