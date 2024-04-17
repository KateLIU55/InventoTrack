package com.example.myinventotrack;

import android.content.ClipData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myinventotrack.database.Entities.Product;
import com.example.myinventotrack.database.InventoTrackDatabase;

public class AddRemoveItem extends AppCompatActivity {

    EditText itemNameEditText;
    EditText itemQuantityEditText;
    EditText itemPriceEditText;
    InventoTrackDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_item);

        // Initialize database
        database = InventoTrackDatabase.getDatabase(this);

        // Initialize views
        itemNameEditText = findViewById(R.id.editTextItemName);
        itemQuantityEditText = findViewById(R.id.editTextItemQuantity);
        itemPriceEditText = findViewById(R.id.editTextItemPrice);
        Button addButton = findViewById(R.id.buttonAddItem);

        // Set onClickListener for add button
        addButton.setOnClickListener(v -> addItem());
    }

    void addItem() {
        // Get user input
        String itemName = itemNameEditText.getText().toString().trim();
        String itemQuantityStr = itemQuantityEditText.getText().toString().trim();
        String itemPriceStr = itemPriceEditText.getText().toString().trim();

        // Validate input
        if (itemName.isEmpty() || itemQuantityStr.isEmpty() || itemPriceStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse quantity and price
        int itemQuantity, itemPrice;
        try {
            itemQuantity = Integer.parseInt(itemQuantityStr);
            itemPrice = Integer.parseInt(itemPriceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid quantity or price", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create Item object
        ClipData.Item item = new ClipData.Item(itemName);

        // Insert item into database
        new InsertItemTask().execute(item);

        // Display success message
        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();

        // Clear input fields
        itemNameEditText.setText("");
        itemQuantityEditText.setText("");
        itemPriceEditText.setText("");
    }

    private class InsertItemTask extends AsyncTask<ClipData.Item, Void, Void> {
        @Override
        protected Void doInBackground(ClipData.Item... items) {
            if (InventoTrackDatabase.productDao() == null) {
                throw new IllegalStateException("ProductDao is null");
            }
            return null;
        }
    }
}
