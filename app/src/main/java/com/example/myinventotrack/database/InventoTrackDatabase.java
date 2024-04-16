package com.example.myinventotrack.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myinventotrack.MainActivity;
import com.example.myinventotrack.database.Entities.InventoTrack;
import com.example.myinventotrack.database.Entities.Product;
import com.example.myinventotrack.database.Entities.User;
import com.example.myinventotrack.database.typeConverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project02: Invento Track Application
 * @date: Since 4/11/2024
 * @Authors: Kate Liu & Samuel Caesar
 * InventoTrackDatabase serves as the primary database setup for the InventoTrack application,
 * handling the storage and management of entities such as InventoTrack, User, and Product.
 * It utilizes Room Database to facilitate app-wide data persistence and operations.
 * This class includes:
 * - Type converters for handling custom data types.
 * - A thread pool executor for handling database operations asynchronously.
 * - Initialization and pre-population of the database with default users during creation.
 */

@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {InventoTrack.class, User.class, Product.class}, version = 1, exportSchema = false)
public abstract class InventoTrackDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "InventoTrack_database";
    public static final String INVENTO_TRACK_TABLE = "inventoTrackTable";

    private static volatile InventoTrackDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static InventoTrackDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (InventoTrackDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    InventoTrackDatabase.class, "inventoTrack_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        User user1 = new User("testuser1", "testuser1", false);
                                        User user2 = new User("admin2", "admin2", true);
                                        getDatabase(context).userDao().insert(user1);
                                        getDatabase(context).userDao().insert(user2);
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract InventoTrackDAO inventoTrackDAO();
    public abstract UserDao userDao();
    public abstract ProductDao productDao();

}