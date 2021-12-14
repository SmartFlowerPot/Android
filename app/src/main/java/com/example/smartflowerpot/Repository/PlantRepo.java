package com.example.smartflowerpot.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.API.PlantAPI;
import com.example.smartflowerpot.database.PlantDAO;
import com.example.smartflowerpot.database.PlantDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlantRepo {
    private static PlantRepo instance;
    private PlantAPI plantAPI;
    private PlantDAO plantDAO;

    ExecutorService executorService;
    Handler mainThreadHandler;

    private LiveData<List<Plant>> allDbPlants;

    private PlantRepo(Application application) {
        PlantDatabase database = PlantDatabase.getInstance(application);
        plantDAO = database.getPlantDAO();
        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

        allDbPlants = plantDAO.getAllPlants();

        plantAPI = PlantAPI.getInstance();
    }

    public static synchronized PlantRepo getInstance(Application application) {
        if(instance == null)
            instance = new PlantRepo(application);
        return instance;
    }

    public MutableLiveData<Plant> getPlantFromAPI() {
        return plantAPI.getPlant();
    }

    public void getPlantInfoFromAPI(String plantID) {
        plantAPI.getPlantInfo(plantID);
    }

    public MutableLiveData<List<Plant>> getPlantsResponseFromAPI() {

        MutableLiveData<List<Plant>> plants = plantAPI.getPlants();

        plants.observeForever(new Observer<List<Plant>>() {
            @Override
            public void onChanged(List<Plant> plants) {
                if (plants.isEmpty()){
                    deleteAllPlantsFromDB();
                } else {
                    savePlantsToDB(plants);
                }
            }
        });

        return plants;
    }

    public void getPlantsFromAPI(String username){
        plantAPI.getPlants(username);

    }

    private void savePlantsToDB(List<Plant> plants) {
        if (plants != null){
            for (Plant plant: plants) {
                executorService.execute(() -> plantDAO.insert(plant));
            }
        }
    }

    public LiveData<List<Plant>> getPlantsFromDB(){
        return allDbPlants;
    }


    public void createAPlant(String username, Plant plant) {
        plantAPI.createAPlant(username, plant);
    }

    public void deletePlant(String eui)  {
        plantAPI.deletePlant(eui);
    }

    public void ControlWindow( String eui, int toOpen) {
        plantAPI.ControlWindow(eui,  toOpen);
    }

    public void deleteAllPlantsFromDB(){
        executorService.execute(() -> plantDAO.deleteAll());
    }
}