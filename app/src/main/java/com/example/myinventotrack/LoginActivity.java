package com.example.myinventotrack;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myinventotrack.database.Entities.User;
import com.example.myinventotrack.databinding.ActivityLoginBinding;

/**
 * Project02: Invento Track Application
 * @date: Since 4/11/2024
 * @version 1.0
 * @Authors: Kate Liu & Samuel Caesar
 * LoginActivity handles the user authentication process, allowing users to log in or navigate to the sign-up page.
 * It uses SharedPreferences for session management to persist login states and auto-navigate to the MainActivity if already logged in.
 */

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        setUpObserver();

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

    public void setUpObserver() {
        userViewModel.userMessage.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            if (message.contains("Login successful")) {
                navigateToMainActivity();
            }
        });
    }
//for unit test to access the method, changed the visibility from private to public
    public void performLogin() {
        String username = binding.editTextUsername.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();
        userViewModel.getUserByUsername(username).observe(this, user -> {
            if (user != null && user.getPassword().equals(password)) {
                saveUserSession(user, username);
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
            }
        });
        userViewModel.loginUser(username, password);
    }
    public void saveUserSession(User user, String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("username", username);
        editor.putBoolean("isAdmin", user.isAdmin());
        editor.apply();
    }

//for unit test to access the method, changed the visibility from private to public
    public void navigateToMainActivity() {
        Log.d("LoginActivity", "Navigating to MainActivity");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateToSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
