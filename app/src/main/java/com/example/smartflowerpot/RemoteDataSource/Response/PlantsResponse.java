package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Plant;

import java.util.ArrayList;
import java.util.List;

public class PlantsResponse {
    private List<Plant> plants;

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }
}
