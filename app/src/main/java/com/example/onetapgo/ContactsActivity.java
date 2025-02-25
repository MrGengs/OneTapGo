package com.example.onetapgo;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactsActivity extends AppCompatActivity {
    private EditText editTextPhoneNumber;
    private Button btnCall, btnSms;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Inisialisasi UI
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        btnCall = findViewById(R.id.btnCall);
        btnSms = findViewById(R.id.btnSms);
        btnBack = findViewById(R.id.btnBack);

        // Tombol Kembali
        btnCall.setOnClickListener(v -> {
            String phoneNumber = editTextPhoneNumber.getText().toString();
            if (!phoneNumber.isEmpty()) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            }
        });

        // Tombol Panggil
        btnCall.setOnClickListener(v -> makePhoneCall());

        // Tombol Kirim SMS
        btnSms.setOnClickListener(v -> sendSmsMessage());
    }

    private void makePhoneCall() {
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Masukkan nomor telepon!");
            return;
        }

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    private void sendSmsMessage() {
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Masukkan nomor telepon!");
            return;
        }

        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:" + phoneNumber));
        startActivity(smsIntent);
    }
}