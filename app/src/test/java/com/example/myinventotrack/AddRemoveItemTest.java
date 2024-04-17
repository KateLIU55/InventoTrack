package com.example.myinventotrack;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myinventotrack.database.InventoTrackDatabase;
import com.example.myinventotrack.database.ProductDao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
    public class AddRemoveItemTest {

        @Mock
        EditText itemNameEditText;

        @Mock
        EditText itemQuantityEditText;

        @Mock
        EditText itemPriceEditText;

        @Mock
        Button addButton;

        @Mock
        Toast toast;

        @Mock
        InventoTrackDatabase database;

        private AddRemoveItem addRemoveItem;

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
            addRemoveItem = new AddRemoveItem();
            addRemoveItem.itemNameEditText = itemNameEditText;
            addRemoveItem.itemQuantityEditText = itemQuantityEditText;
            addRemoveItem.itemPriceEditText = itemPriceEditText;
            addRemoveItem.database = database;
        }

    @Test
    public void testAddItem_withValidInput() {
        // Mock user input
        String itemName = "Item";
        String itemQuantity = "10";
        String itemPrice = "50";

        // Stubbing EditText
        when(itemNameEditText.getText()).thenReturn(mock(Editable.class));
        when(itemNameEditText.getText().toString()).thenReturn(itemName);
        when(itemQuantityEditText.getText()).thenReturn(mock(Editable.class));
        when(itemQuantityEditText.getText().toString()).thenReturn(itemQuantity);
        when(itemPriceEditText.getText()).thenReturn(mock(Editable.class));
        when(itemPriceEditText.getText().toString()).thenReturn(itemPrice);

        // Stubbing database operation
        ProductDao productDao = mock(ProductDao.class);
        when(InventoTrackDatabase.productDao()).thenReturn(productDao);

        // Stubbing Toast
        when(Toast.makeText(any(), any(), anyInt())).thenReturn(toast);

        // Call the addItem method
        addRemoveItem.addItem();

        // Verify that the appropriate database operation is invoked
        InventoTrackDatabase.productDao();

        // Verify that success message is displayed
        verify(toast).show();
    }

    @Test
    public void testAddItem_withEmptyInput() {
        // Mock user input
        String itemName = "";
        String itemQuantity = "";
        String itemPrice = "";

        // Stubbing EditText
        when(itemNameEditText.getText()).thenReturn(mock(Editable.class));
        when(itemNameEditText.getText().toString()).thenReturn(itemName);
        when(itemQuantityEditText.getText()).thenReturn(mock(Editable.class));
        when(itemQuantityEditText.getText().toString()).thenReturn(itemQuantity);
        when(itemPriceEditText.getText()).thenReturn(mock(Editable.class));
        when(itemPriceEditText.getText().toString()).thenReturn(itemPrice);

        // Stubbing Toast
        when(Toast.makeText(any(), any(), anyInt())).thenReturn(toast);

        // Call the addItem method
        addRemoveItem.addItem();

        // Verify that no database operation is invoked
        verify(database).productDao();

        // Verify that error message is displayed
        verify(toast).show();
    }
}
