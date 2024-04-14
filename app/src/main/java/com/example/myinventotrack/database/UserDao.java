package com.example.myinventotrack.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myinventotrack.database.Entities.User;

import java.util.List;

@Dao
public interface UserDao {
    void insertUser(User user);

    User getUser(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    User getUsername(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("Select * from users")
    List<User> getAllUsers();

}

