package com.example.smartflowerpot.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.smartflowerpot.Model.Plant;

@Database(entities = {Plant.class}, version = 4)
public abstract class PlantDatabase extends RoomDatabase {
    private static PlantDatabase instance;
    public  abstract PlantDAO getPlantDAO();


    public static synchronized PlantDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PlantDatabase.class, "plant_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
