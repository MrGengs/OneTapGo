package com.example.onetapgo;

import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnAppInfo = findViewById(R.id.btnAppInfo);
        btnAppInfo.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.this, InfoAplikasiActivity.class);
            startActivity(intent);
        });
    }
}