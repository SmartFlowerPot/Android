package com.example.smartflowerpot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.ViewModel.PlantsOverviewViewModel;

public class CreateFragment extends Fragment {
    private EditText nicknameField;
    private EditText deviceIdentifierField;
    private Button createPlantbtn;
    private View view;
    private PlantsOverviewViewModel plantsOverviewViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity)getActivity()).setTopbarTitle("New plant");
    }

    private void createPlant() {
        Plant plant = new Plant("", nicknameField.getText().toString(), deviceIdentifierField.getText().toString());
        plantsOverviewViewModel.createAPlant("karlo", plant);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create, container, false);

        nicknameField = view.findViewById(R.id.nicknameField);
        deviceIdentifierField = view.findViewById(R.id.deviceIdentifierField);
        createPlantbtn = view.findViewById(R.id.createPlantBtn);

        plantsOverviewViewModel = new ViewModelProvider(this).get(PlantsOverviewViewModel.class);

        createPlantbtn.setOnClickListener(r -> createPlant());

        return view;
    }


}