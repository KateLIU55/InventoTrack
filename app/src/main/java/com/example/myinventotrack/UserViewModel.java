package com.example.myinventotrack;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myinventotrack.database.Entities.User;
import com.example.myinventotrack.database.InventoTrackDatabase;
import com.example.myinventotrack.database.UserDao;

/**
 * Project02: Invento Track Application
 * @date: Since 4/11/2024
 * @Authors: Kate Liu & Samuel Caesar
 * UserViewModel manages all user-related data interactions between the app's UI and the database.
 * It handles operations such as user login, signup, and deletion, encapsulating all business logic
 * for user management within the app.
 * LiveData is used to observe and respond to data changes asynchronously.
 */

public class UserViewModel extends AndroidViewModel {
    private UserDao userDao;
    public MutableLiveData<String> userMessage = new MutableLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
        userDao = InventoTrackDatabase.getDatabase(application).userDao();
    }

    public LiveData<User> getUserByUsername(String username) {
        MutableLiveData<User> liveData = new MutableLiveData<>();
        new Thread(() -> {
            User user = userDao.findUserByUsername(username);
            liveData.postValue(user);
        }).start();
        return liveData;
    }

    public void loginUser(String username, String password) {
        new Thread(() -> {
            User user = userDao.findUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                userMessage.postValue("Login successful: " + username);
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putBoolean("isAdmin", user.isAdmin());
                editor.putString("username", username);
                editor.apply();
            } else {
                userMessage.postValue("Invalid username or password");
            }
        }).start();
    }
    public void signUpNewUser(String username, String password, boolean isAdmin) {
        new Thread(() -> {
            if (userDao.usernameExists(username)) {
                userMessage.postValue("Username already taken.");
            } else {
                userDao.insertUser(new User(username, password, isAdmin));
                userMessage.postValue("Signup successful, please login.");
            }
        }).start();
    }
    public void deleteUser(String username) {
        new Thread(() -> {
            userDao.deleteUserByUsername(username);
            userMessage.postValue("User deleted successfully.");
        }).start();
    }


}
