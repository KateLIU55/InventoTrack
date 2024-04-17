package com.example.myinventotrack;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myinventotrack.database.Entities.Sale;
import com.example.myinventotrack.database.InventoTrackDatabase;

import java.util.List;

public class DailySales extends AppCompatActivity {

    private TextView textViewUser;
    private TextView textViewProductName;
    private TextView textViewQuantity;
    private TextView textViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sales);

        // Initialize views
        textViewUser = findViewById(R.id.textViewUser);
        textViewProductName = findViewById(R.id.textViewProductName);
        textViewQuantity = findViewById(R.id.textViewQuantity);
        textViewPrice = findViewById(R.id.textViewPrice);
        Button buttonPrint = findViewById(R.id.buttonPrint);

        // Retrieve sales data from the database
        InventoTrackDatabase.getDatabase(this);
        assert InventoTrackDatabase.saleDao() != null;
        List<Sale> sales = InventoTrackDatabase.saleDao().getAllSales();

        // Display the sales data
        displaySalesData(sales);

        // Set onClickListener for the print button
        buttonPrint.setOnClickListener(v -> {
            // Implement printing functionality here
            Toast.makeText(this, "Printing functionality not implemented yet", Toast.LENGTH_SHORT).show();
        });
    }

    private void displaySalesData(List<Sale> sales) {
        StringBuilder userData = new StringBuilder();
        StringBuilder productNameData = new StringBuilder();
        StringBuilder quantityData = new StringBuilder();
        StringBuilder priceData = new StringBuilder();

        // Iterate over the list of sales and concatenate the data
        for (Sale sale : sales) {
            userData.append("User: ").append(sale.getUser()).append("\n");
            productNameData.append("Product Name: ").append(sale.getItem()).append("\n");
            quantityData.append("Quantity: ").append(sale.getQuantity()).append("\n");
            priceData.append("Total: $").append(sale.getTotal()).append("\n");
        }

        // Set the concatenated data to TextViews
        textViewUser.setText(userData.toString());
        textViewProductName.setText(productNameData.toString());
        textViewQuantity.setText(quantityData.toString());
        textViewPrice.setText(priceData.toString());
    }

}

