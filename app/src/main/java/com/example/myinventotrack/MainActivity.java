package com.example.myinventotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myinventotrack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserViewModel userViewModel;
    public static final String TAG = "DAC_INVENTOTRACK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "User");
        boolean isAdmin = sharedPreferences.getBoolean("isAdmin", false);
        binding.textViewWelcome.setText("Welcome, " + username + "!");
        binding.buttonNewSale.setOnClickListener(v -> newSale());
        binding.buttonTodaysSales.setOnClickListener(v -> todaysSales());
        binding.buttonReturns.setOnClickListener(v -> returns());

        if (isAdmin) {
            binding.buttonAdminArea.setVisibility(View.VISIBLE);
        } else {
            binding.buttonAdminArea.setVisibility(View.INVISIBLE);
        }
        binding.buttonLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        binding.buttonAdminArea.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdminAreaActivity.class);
            startActivity(intent);
        });
        binding.buttonNewSale.setOnClickListener(v -> {
            // Start the NewSaleActivity when the "New Sale" button is clicked
            startActivity(new Intent(MainActivity.this, NewSales.class));
        });

    }

    private void returns() {
        Intent intent = new Intent(MainActivity.this, Returns.class);
        startActivity(intent);
    }

    private void todaysSales() {
        Intent intent = new Intent(MainActivity.this, DailySales.class);
        startActivity(intent);
    }

    private void newSale() {
        Intent intent = new Intent(MainActivity.this, NewSales.class);
        startActivity(intent);
    }
}