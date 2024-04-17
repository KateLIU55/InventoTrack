package com.example.myinventotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

/**
 * Project02: Invento Track Application
 * @date: Since 4/11/2024
 * @version 1.0
 * @Authors: Kate Liu & Samuel Caesar
 * ManageUsers is responsible for administering user accounts within the app.
 * It provides functionality for adding new users and deleting existing users.
 * The ability to assign administrator privileges is available through a switch
 * that is only visible to users logged in with admin privileges.
 */

public class ManageUsers extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Switch isAdminSwitch;
    private Button signUpButton;
    private Button deleteUserButton;
    private UserViewModel userViewModel;  // Declare UserViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        usernameInput = findViewById(R.id.newAccountInputEditText);
        passwordInput = findViewById(R.id.setPasswordInputEditText);
        isAdminSwitch = findViewById(R.id.switch1);
        signUpButton = findViewById(R.id.signUpButton);
        deleteUserButton = findViewById(R.id.buttonDeleteUser);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> logout());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        isAdminSwitch.setVisibility(isAdmin ? View.VISIBLE : View.GONE);

        signUpButton.setOnClickListener(v -> addUser());
        deleteUserButton.setOnClickListener(v -> deleteUser());
    }

    private void addUser() {
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        boolean isAdmin = isAdminSwitch.isChecked();

        if (!username.isEmpty() && !password.isEmpty()) {
            userViewModel.signUpNewUser(username, password, isAdmin);  // Use signUpNewUser method
            Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Username or password cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteUser() {
        String username = usernameInput.getText().toString().trim();
        if (!username.isEmpty()) {
            userViewModel.deleteUser(username);  // This method needs to be added in UserViewModel
            Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
    private void logout() {
        SharedPreferences preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
