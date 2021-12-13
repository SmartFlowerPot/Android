package com.example.smartflowerpot.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.LightLvl;
import com.example.smartflowerpot.Repository.LightLvlRepo;

public class LightLvlViewModel extends ViewModel {
    private static LightLvlRepo lightLvlRepo;

    public LightLvlViewModel() {
        lightLvlRepo = LightLvlRepo.getInstance();
    }

    public MutableLiveData<LightLvl> getLightLvlResponse() {
        return lightLvlRepo.getLightLvlResponse();
    }

    public void getLightLvlRequest(String eui) {
        lightLvlRepo.getLightLvlRequest(eui);
    }
}
