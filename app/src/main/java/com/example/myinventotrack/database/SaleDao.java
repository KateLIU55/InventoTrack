package com.example.myinventotrack.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myinventotrack.database.Entities.Product;
import com.example.myinventotrack.database.Entities.Sale;

import java.util.List;
@Dao
public interface SaleDao {
    @Insert
    void insertSale(Sale sale);

    @Update
    void updateSale(Sale sale);

    @Delete
    void deleteSale(Sale sale);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();

    @Query("SELECT * FROM products WHERE id = :productId")
    Product getProductById(int productId);
}
