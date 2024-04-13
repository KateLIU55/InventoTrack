package com.example.myinventotrack.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Products")
public class Products {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public double price;
}
