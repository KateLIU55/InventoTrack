package com.example.myinventotrack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myinventotrack.database.Entities.User;
import com.example.myinventotrack.database.InventoTrackDatabase;
import com.example.myinventotrack.database.UserDao;

public class UserViewModel extends AndroidViewModel {
    private final UserDao userDao;
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
            } else {
                userMessage.postValue("Invalid username or password");
            }
        }).start();
    }
    public void signUpNewUser(String username, String password) {
        new Thread(() -> {
            if (userDao.usernameExists(username)) {
                userMessage.postValue("Username already taken.");
            } else {
                userDao.insertUser(new User(username, password, false));
                userMessage.postValue("Signup successful, please login.");
            }
        }).start();
    }
}
