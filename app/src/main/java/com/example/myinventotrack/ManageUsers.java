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

        // Initialize UserViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        isAdminSwitch.setVisibility(isAdmin ? View.VISIBLE : View.GONE);

      //  configureAdminSwitch();

        signUpButton.setOnClickListener(v -> addUser());
        deleteUserButton.setOnClickListener(v -> deleteUser());
    }

//    private void configureAdminSwitch() {
//        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
//        boolean isAdmin = sharedPreferences.getBoolean("isAdmin", false);
//
//        isAdminSwitch.setEnabled(isAdmin && isSuperAdmin());
//    }

//    private boolean isSuperAdmin() {
//        return false;
//    }

    private void addUser() {
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        boolean isAdmin = isAdminSwitch.isChecked();
                //&& isAdminSwitch.getVisibility() == View.VISIBLE;

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
            // This assumes deleteUser is implemented in the UserViewModel
            userViewModel.deleteUser(username);  // This method needs to be added in UserViewModel
            Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
    private void logout() {
        // Assuming SharedPreferences is used for session management
        SharedPreferences preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Navigate back to the LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
