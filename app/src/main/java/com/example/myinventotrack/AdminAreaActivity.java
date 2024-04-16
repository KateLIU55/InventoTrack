package com.example.myinventotrack;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myinventotrack.databinding.ActivityAdminAreaBinding;
/**
 * Project02: Invento Track Application
 * @date: Since 4/11/2024
 * @Authors: Kate Liu & Samuel Caesar
 * AdminAreaActivity serves as the control panel for administrators, providing access to various administrative functions.
 * This activity includes buttons for managing users, handling sales data, modifying inventory, and accessing system settings.
 * Each button launches a different activity corresponding to the specific administrative task.
 */
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
        Intent intent = new Intent ( AdminAreaActivity.this, Settings.class);
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(AdminAreaActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}