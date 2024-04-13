package com.example.myinventotrack.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String username;
    public String password;
    public boolean isAdmin;

}
