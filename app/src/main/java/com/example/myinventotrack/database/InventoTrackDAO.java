package com.example.myinventotrack.database;

import static com.example.myinventotrack.database.InventoTrackDatabase.INVENTO_TRACK_TABLE;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.myinventotrack.database.Entities.InventoTrack;
import java.util.ArrayList;

@Dao
public interface InventoTrackDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InventoTrack inventoTrack);
    @Query("Select * from " + INVENTO_TRACK_TABLE)
    ArrayList<InventoTrack> getAllRecords();
}
