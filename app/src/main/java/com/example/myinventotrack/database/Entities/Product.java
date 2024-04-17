package com.example.myinventotrack.database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Project02: Invento Track Application
 * @date Since 4/11/2024
 * @version 1.0
 * @Authors Kate Liu & Samuel Caesar
 * Entity class for products in the InventoTrack inventory management system. This class defines the product's
 * properties such as ID, name, and price, using Room database annotations to facilitate data persistence.
 * It functions alongside other entities like InventoTrack to maintain comprehensive inventory records.
 */

@Entity(tableName = "Products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public double price;
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
