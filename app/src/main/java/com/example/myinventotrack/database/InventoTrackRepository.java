package com.example.myinventotrack.database;

import android.app.Application;
import android.util.Log;

import com.example.myinventotrack.database.Entities.InventoTrack;
import com.example.myinventotrack.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class InventoTrackRepository {
    private InventoTrackDAO inventoTrackDAO;
    private ArrayList<InventoTrack> allLogs;

    public InventoTrackRepository(Application application) {
        InventoTrackDatabase db = InventoTrackDatabase.getDatabase(application);
        this.inventoTrackDAO = db.inventoTrackDAO();
        this.allLogs = this.inventoTrackDAO.getAllRecords();
    }
    public ArrayList<InventoTrack> getAllLogs() {
        Future<ArrayList<InventoTrack>> future = InventoTrackDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<InventoTrack>>() {
                    @Override
                    public ArrayList<InventoTrack> call() throws Exception {
                        return inventoTrackDAO.getAllRecords();
                    }
                }
        );
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
           // e.printStackTrace();
            Log.i(MainActivity.TAG, "Problem when getting all inventoTracks in the repository");
        }
        return null;
    }
    public void insertInventoTrack(InventoTrack inventoTrack) {
        InventoTrackDatabase.databaseWriteExecutor.execute(()->
        {
            inventoTrackDAO.insert(inventoTrack);
        });
    }
}
