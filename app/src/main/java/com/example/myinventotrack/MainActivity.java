package com.example.myinventotrack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myinventotrack.databinding.ActivityMainBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            
           if(validateLogin(username, password)) {
               displayUserInfo(username);
           } else {
               Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show();
           }
    } catch (Exception e) {
            Log.e(TAG,"LoginError: Error during login", e);
            Toast.makeText(this, "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateLogin(String username, String password) {
        return username.equals("validUser") && password.equals("validPassword");
    }

   private void displayUserInfo(String username) {
       LocalDateTime now = LocalDateTime.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String formattedDateTime = now.format(formatter);

       binding.logDisplayTextView.setText("Logged in as: " + username + "\nAt: " + formattedDateTime);
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