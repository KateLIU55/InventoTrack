package com.example.myinventotrack;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myinventotrack.database.Entities.Sale;
import com.example.myinventotrack.database.InventoTrackDatabase;
import com.example.myinventotrack.database.SaleDao;

public class NewSales extends AppCompatActivity {

    private EditText titleNewUser;
    private TextView item1, quantity1, price1, totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sales);

        // Initialize views
        titleNewUser = findViewById(R.id.titleNewUser);
        item1 = findViewById(R.id.item1);
        quantity1 = findViewById(R.id.quantity1);
        price1 = findViewById(R.id.price1);
        totalPrice = findViewById(R.id.totalPrice);
        Button buttonPayment = findViewById(R.id.buttonPayment);

        // Setup onClickListener for payment button
        buttonPayment.setOnClickListener(v -> processPayment());
    }

    // Function to process payment
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

        // Get database instance
        InventoTrackDatabase database = InventoTrackDatabase.getDatabase(this);

        // Get SaleDao from the database
        SaleDao saleDao = InventoTrackDatabase.saleDao();

        // Create a new Sale object with input data
        Sale sale = new Sale(user, item, quantityInt, priceDouble, total);

        // Insert the Sale object into the database
        new InsertSaleTask(saleDao).execute(sale);

        // Toast message showing successful payment
        Toast.makeText(this, "Payment processed for " + total, Toast.LENGTH_SHORT).show();
    }

    private static class InsertSaleTask extends AsyncTask<Sale, Void, Void> {
        private final SaleDao saleDao;

        InsertSaleTask(SaleDao saleDao) {
            this.saleDao = saleDao;
        }

        @Override
        protected Void doInBackground(Sale... sales) {
            saleDao.insertSale(sales[0]);
            return null;
        }
    }
}
