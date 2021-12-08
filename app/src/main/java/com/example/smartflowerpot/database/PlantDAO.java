package com.example.smartflowerpot.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.example.smartflowerpot.Model.Plant;

@Dao
public interface PlantDAO {
    @Insert
    void insert(Plant plant);

    @Delete
    void delete(Plant plant);
}
