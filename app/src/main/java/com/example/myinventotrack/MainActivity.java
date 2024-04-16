package com.example.myinventotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        String username = sharedPreferences.getString("username", "User");
        boolean isAdmin = sharedPreferences.getBoolean("isAdmin", false);
        binding.textViewWelcome.setText("Welcome, " + username + "!");
        binding.buttonNewSale.setOnClickListener(v -> newSale());
        binding.buttonTodaysSales.setOnClickListener(v -> todaysSales());
        binding.buttonReturns.setOnClickListener(v -> returns());

        if (isLoggedIn) {
            if (isAdmin) {
                adjustUIForAdmin();
            } else {
                adjustUIForRegularUser();
            }
        } else {

            navigateToLoginActivity();
        }

        if(isAdmin) {
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
            Intent intent = new Intent(MainActivity.this, ManageUsers.class);
            intent.putExtra("isAdmin", sharedPreferences.getBoolean("isAdmin", false));

            startActivity(intent);
        });
        binding.buttonNewSale.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NewSales.class));
        });

    }
    private void adjustUIForAdmin() {
        binding.buttonAdminArea.setVisibility(View.VISIBLE);
    }

    private void adjustUIForRegularUser() {
        binding.buttonAdminArea.setVisibility(View.INVISIBLE);
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void returns() {
        Toast.makeText(this, "Returns", Toast.LENGTH_SHORT).show();
    }

    private void todaysSales() {
        Toast.makeText(this, "Today's Sales", Toast.LENGTH_SHORT).show();
    }

    private void newSale() {
        Toast.makeText(this, "New Sale", Toast.LENGTH_SHORT).show();
    }
}