package com.example.onetapgo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {
    private TextInputEditText nameEditText;
    private TextInputEditText emailEditText;
    private ImageView profileImage;
    private MaterialButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupToolbar();
        initializeViews();
        loadUserData();
        setupListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeViews() {
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        profileImage = findViewById(R.id.profileImage);
        saveButton = findViewById(R.id.saveButton);
    }

    private void loadUserData() {
        nameEditText.setText("John Doe");
        emailEditText.setText("john.doe@example.com");
    }

    private void setupListeners() {
        saveButton.setOnClickListener(v -> saveProfile());
        profileImage.setOnClickListener(v -> selectImage());
    }

    private void saveProfile() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
    }

    private void selectImage() {
        // Implement image selection logic
    }
}
