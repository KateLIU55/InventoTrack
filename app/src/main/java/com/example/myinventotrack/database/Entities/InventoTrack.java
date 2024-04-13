package com.example.myinventotrack.database.Entities;

import android.os.Build;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myinventotrack.database.InventoTrackDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(tableName = InventoTrackDatabase.INVENTO_TRACK_TABLE)
public class InventoTrack {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private LocalDateTime dateTime;

    public InventoTrack(String username, LocalDateTime dateTime) {
        this.username = username;
        this.dateTime = dateTime;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            dateTime = LocalDateTime.now();
//        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoTrack that = (InventoTrack) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, dateTime);
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

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}

