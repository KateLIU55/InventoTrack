package com.example.myinventotrack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myinventotrack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //private Log log;
    public static final String TAG = "DAC_INVENTOTRACK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignup();
            }
        });
    }

    private void performLogin() {
        try{
            String username = binding.userNameInputEditText.getText().toString().trim();
            String password = binding.passwordInputEditText.getText().toString().trim();
            
            Log.d("LoginAttempt", "Username: " + username + ", Password: " + password);
            
            validateLogin(username, password);
            
    } catch (Exception e) {
            Log.e("LoginError", "Error during login", e);
            Toast.makeText(this, "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void validateLogin(String username, String password) {
    }

    private void performSignup() {
        String newUsername = binding.newAccountInputEditText.getText().toString().trim();
        String newPassword = binding.setPasswordInputEditText.getText().toString().trim();
        
        Log.d("SignUpAttemp", "New Username: " + newUsername + ", New Password: " + newPassword);
        
        handleSignUp(newUsername, newPassword);
    }

    private void handleSignUp(String newUsername, String newPassword) {
    }


}