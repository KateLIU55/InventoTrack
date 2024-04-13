package com.example.myinventotrack.database;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.myinventotrack.database.Entities.User;

public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User WHERE username =:username AND password =:password")
    User getUser(String username, String password);
}
