package com.example.myinventotrack;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myinventotrack.databinding.ActivityMainBinding;

public class MainActivity<SignUpCallback> extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserViewModel userViewModel;
    public static final String TAG = "DAC_INVENTOTRACK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.userMessage.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            hideKeyboard();
        });

        binding.logButton.setOnClickListener(v -> performLogin());
        binding.signUpButton.setOnClickListener(v -> performSignup());
    }
    private void performLogin() {
        String username = binding.userNameInputEditText.getText().toString().trim();
        String password = binding.passwordInputEditText.getText().toString().trim();
        userViewModel.loginUser(username, password);
        hideKeyboard();
    }
//   private void hideKeyboard(View view) {
//       InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//       if(inputMethodManager != null) {
//           inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
//       }
//   }

    private void performSignup() {
        String newUsername = binding.newAccountInputEditText.getText().toString().trim();
        String newPassword = binding.setPasswordInputEditText.getText().toString().trim();
        userViewModel.signUpNewUser(newUsername, newPassword);
        hideKeyboard();
    }
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}