package com.example.myinventotrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myinventotrack.databinding.ActivityAdminAreaBinding;

public class AdminAreaActivity extends AppCompatActivity {
    private ActivityAdminAreaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAreaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonManageUsers.setOnClickListener(v -> manageUsers());
        binding.buttonGenerateReports.setOnClickListener(v -> generateReports());
        binding.buttonSettings.setOnClickListener(v -> openSettings());
        binding.buttonLogout.setOnClickListener(v -> logout());
    }
    private void manageUsers() {
        Toast.makeText(this, "Open User Management", Toast.LENGTH_SHORT).show();
    }

    private void generateReports() {
        Toast.makeText(this, "Generate Reports", Toast.LENGTH_SHORT).show();
    }

    private void openSettings() {
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
    }

    private void logout() {
        Intent intent = new Intent(AdminAreaActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}