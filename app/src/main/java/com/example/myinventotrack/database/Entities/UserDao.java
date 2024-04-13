package com.example.myinventotrack.database.Entities;

import androidx.room.Insert;
import androidx.room.Query;

public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User WHERE username =:username AND password =:password")
    User getUser(String username, String password);
}
