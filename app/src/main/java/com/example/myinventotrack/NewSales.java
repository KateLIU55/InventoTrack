package com.example.myinventotrack;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewSales extends AppCompatActivity {

    private EditText titleNewUser;
    private TextView item1, quantity1, price1, totalPrice;

    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
                setContentView(R.layout.activity_new_sales);

                // Initialize views
                titleNewUser = findViewById(R.id.titleNewUser);
                item1 = findViewById(R.id.item1);
                quantity1 = findViewById(R.id.quantity1);
                price1 = findViewById(R.id.price1);
                totalPrice = findViewById(R.id.totalPrice);
                Button buttonPayment = findViewById(R.id.buttonPayment);

                // Setup any necessary logic or functionality here
                // For example, setting text, handling button clicks, etc.
            }

    // Function to process payment does not work completely yet
    @SuppressLint("StringFormatInvalid")
    private void processPayment() {
        // Retrieve input values
        String user = titleNewUser.getText().toString().trim();
        String item = item1.getText().toString().trim();
        String quantity = quantity1.getText().toString().trim();
        String price = price1.getText().toString().trim();

        // Validate input
        if (user.isEmpty() || item.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert quantity and price to integers or doubles
        int quantityInt = Integer.parseInt(quantity);
        double priceDouble = Double.parseDouble(price);

        // Calculate total price
        double total = quantityInt * priceDouble;

        // Display total price
        totalPrice.setText(getString(R.string.total, total));

        // Additional logic for updating database

        // toast message showing successful payment
        Toast.makeText(this, "Payment processed for " + total, Toast.LENGTH_SHORT).show();
    }
}








