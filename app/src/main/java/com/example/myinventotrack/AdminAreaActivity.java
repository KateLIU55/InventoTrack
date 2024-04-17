package com.example.myinventotrack;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myinventotrack.databinding.ActivityAdminAreaBinding;

public class AdminAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.myinventotrack.databinding.ActivityAdminAreaBinding binding = ActivityAdminAreaBinding.inflate(getLayoutInflater());
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
        Intent intent = new Intent(AdminAreaActivity.this, Returns.class);
        startActivity(intent);
    }

    private void weeklySales() {
        Intent intent = new Intent ( AdminAreaActivity.this, WeeklySales.class);
        startActivity(intent);
    }

    private void newSale() {
        Intent intent = new Intent ( AdminAreaActivity.this, NewSales.class);
        startActivity(intent);
    }

    private void dailySalesReport() {
        Intent intent = new Intent ( AdminAreaActivity.this, DailySales.class);
        startActivity(intent);
    }

    private void addRemoveItem() {
        Intent intent = new Intent ( AdminAreaActivity.this, AddRemoveItem.class);
        startActivity(intent);
    }

    private void manageUsers() {
        Intent intent = new Intent ( AdminAreaActivity.this, ManageUsers.class);
        startActivity(intent);
    }
    private void openSettings() {
        // Create an Intent to open the Settings activity
        Intent intent = new Intent(AdminAreaActivity.this, Settings.class);

        // Start the Settings activity
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(AdminAreaActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}