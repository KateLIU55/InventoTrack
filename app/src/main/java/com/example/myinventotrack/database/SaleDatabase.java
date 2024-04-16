package com.example.myinventotrack.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myinventotrack.database.Entities.Sale;

@Database(entities = {Sale.class}, version = 1)
public abstract class SaleDatabase extends RoomDatabase {
    private static volatile SaleDatabase INSTANCE;

    public abstract SaleDao saleDao();

    public static SaleDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SaleDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SaleDatabase.class, "sale_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
