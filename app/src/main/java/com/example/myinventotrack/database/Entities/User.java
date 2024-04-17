package com.example.myinventotrack.database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Project02: Invento Track Application
 * @date: Since 4/11/2024
 * @version 1.0
 * @Authors: Kate Liu & Samuel Caesar
 * Entity representing a User in the InventoTrack application.
 * This class defines the User's schema for the Room database, including fields for ID,
 * username, password, and admin status.
 */

@Entity(tableName = "Users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String username;
    public String password;
    public boolean isAdmin;
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
