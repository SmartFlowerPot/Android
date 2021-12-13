package com.example.smartflowerpot.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.Repository.CO2Repo;

public class CO2ViewModel extends ViewModel {
    private static CO2Repo co2Repo;

    public CO2ViewModel() {

        co2Repo = CO2Repo.getInstance();

    }

    public MutableLiveData<CO2> getCO2() {

        return co2Repo.getCO2();
    }

    public void getCO2Request(String eui){

        co2Repo.getCO2Request(eui);
    }
    public void ControlWindow( String eui, int open_close_window) {
        co2Repo.ControlWindow( eui, open_close_window);
    }
}
