package com.example.myinventotrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myinventotrack.databinding.ActivityAdminAreaBinding;

public class AdminAreaActivity extends AppCompatActivity {
    private ActivityAdminAreaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAreaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonManageUsers.setOnClickListener(v -> manageUsers());
        binding.buttonSettings.setOnClickListener(v -> openSettings());
        binding.buttonLogout.setOnClickListener(v -> logout());
        binding.buttonAddRemoveItem.setOnClickListener(v -> addRemoveItem());
        binding.buttonDailySalesReport.setOnClickListener(v -> dailySalesReport());
        binding.buttonNewSale.setOnClickListener(v -> newSale());
        binding.buttonWeeklySales.setOnClickListener(v -> weeklySales());
        binding.buttonReturns.setOnClickListener(v -> returns());
    }

    private void returns() {
        Toast.makeText(this, "Return items", Toast.LENGTH_SHORT).show();
    }

    private void weeklySales() {
        Toast.makeText(this, "Weekly Sales", Toast.LENGTH_SHORT).show();
    }

    private void newSale() {
        Toast.makeText(this, "New Sale", Toast.LENGTH_SHORT).show();
    }

    private void dailySalesReport() {
        Toast.makeText(this, "Daily Sales Report", Toast.LENGTH_SHORT).show();
    }

    private void addRemoveItem() {
        Toast.makeText(this, "Add or Remove Item", Toast.LENGTH_SHORT).show();
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