package com.example.myinventotrack.Database;

import static com.example.myinventotrack.Database.InventoTrackDatabase.INVENTO_TRACK_TABLE;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.myinventotrack.Database.Entities.InventoTrack;
import java.util.ArrayList;

@Dao
public interface InventoTrackDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InventoTrack inventoTrack);
    @Query("Select * from " + INVENTO_TRACK_TABLE)
    ArrayList<InventoTrack> getAllRecords();
}
