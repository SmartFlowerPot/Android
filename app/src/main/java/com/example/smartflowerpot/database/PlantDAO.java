package com.example.smartflowerpot.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.smartflowerpot.Model.Plant;

import java.util.List;

@Dao
public interface PlantDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Plant plant);

    @Delete
    void delete(Plant plant);

    @Query("DELETE FROM plant_table")
    void deleteAll();

    @Query("SELECT * FROM plant_table")
    LiveData<List<Plant>> getAllPlants();
}
