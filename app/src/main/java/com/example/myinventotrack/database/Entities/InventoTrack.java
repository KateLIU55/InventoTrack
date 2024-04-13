package com.example.myinventotrack.database.Entities;

import android.os.Build;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myinventotrack.database.InventoTrackDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity(tableName = InventoTrackDatabase.INVENTO_TRACK_TABLE)
public class InventoTrack {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private LocalDate date;
    private LocalTime time;

    public InventoTrack(String username) {
        this.username = username;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date = LocalDate.now();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            time = LocalTime.now();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoTrack that = (InventoTrack) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, date, time);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

