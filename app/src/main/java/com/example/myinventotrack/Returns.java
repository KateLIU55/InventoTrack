package com.example.myinventotrack;

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

public class Returns extends AppCompatActivity {

    private EditText titleReturnsUser;
    private EditText item1;
    private EditText quantity1;
    private EditText price1;
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returns);

        // Initialize views
        titleReturnsUser = findViewById(R.id.titleReturnsUser);
        item1 = findViewById(R.id.item1);
        quantity1 = findViewById(R.id.quantity1);
        price1 = findViewById(R.id.price1);
        totalPrice = findViewById(R.id.totalPrice);
        Button buttonReturn = findViewById(R.id.buttonReturn);

        // Setup onClickListener for return button
        buttonReturn.setOnClickListener(v -> processReturn());
    }

    private void processReturn() {
        // Retrieve input values
        String user = titleReturnsUser.getText().toString().trim();
        String item = item1.getText().toString().trim();
        String quantity = quantity1.getText().toString().trim();
        String price = price1.getText().toString().trim();

        // Validate input
        if (user.isEmpty() || item.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert quantity and price to integers or doubles
        int quantityInt;
        double priceDouble;
        try {
            quantityInt = Integer.parseInt(quantity);
            priceDouble = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid quantity or price format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate total price
        double total = quantityInt * priceDouble;

        // Display total price
        totalPrice.setText(getString(R.string.total, total));

        // Create a new Sale object with negative quantity and total for return
        Sale returnSale = new Sale(user, item, -quantityInt, priceDouble, -total);

        // Insert the return Sale object into the database
        new UpdateSaleTask(InventoTrackDatabase.getDatabase(this).saleDao()).execute(returnSale);

        // Toast message showing successful return
        Toast.makeText(this, "Return processed", Toast.LENGTH_SHORT).show();
    }

    private static class UpdateSaleTask extends AsyncTask<Sale, Void, Void> {
        private final SaleDao saleDao;

        UpdateSaleTask(SaleDao saleDao) {
            this.saleDao = saleDao;
        }

        @Override
        protected Void doInBackground(Sale... sales) {
            saleDao.updateSale(sales[0]);
            return null;
        }
    }
}
