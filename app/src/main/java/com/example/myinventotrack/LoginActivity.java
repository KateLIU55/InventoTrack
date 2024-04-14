package com.example.myinventotrack;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myinventotrack.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToMainActivity();
            return;
        }
        binding.buttonLogin.setOnClickListener(v -> {
            performLogin();
            hideKeyboard(v);
        });
        binding.buttonCreateAccount.setOnClickListener(v -> {
            navigateToSignUpActivity();
            hideKeyboard(v);
        });
    }

    private void performLogin() {
        String username = binding.editTextUsername.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();

        userViewModel.userMessage.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            if (message.equals("Login successful")) {
                SharedPreferences sharedPreferences = null;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("username", username);
                editor.apply();
                navigateToMainActivity();
            }
        });

        userViewModel.loginUser(username, password);
    }
    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToSignUpActivity() {
        // Assuming SignUpActivity exists
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
