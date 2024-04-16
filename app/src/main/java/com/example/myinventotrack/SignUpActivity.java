package com.example.myinventotrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.myinventotrack.databinding.ActivitySignUpBinding;
/**
 * Project02: Invento Track Application
 * @date: Since 4/11/2024
 * @Authors: Kate Liu & Samuel Caesar
 * SignUpActivity handles the registration process for new users. It provides a user interface
 * for entering username and password, and a button to submit the registration details.
 * The activity interacts with UserViewModel to create a new user account.
 * If the registration is successful, the user is directed back to the login screen.
 */
public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        String username = binding.editTextNewUsername.getText().toString().trim();
        String password = binding.editTextNewPassword.getText().toString().trim();
        boolean isAdmin = false;

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        userViewModel.signUpNewUser(username, password, isAdmin);
        userViewModel.userMessage.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            if (message.equals("Signup successful, please login.")) {
                finish();
            }
        });
    }
}