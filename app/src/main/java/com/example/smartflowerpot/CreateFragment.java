package com.example.smartflowerpot;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Activity.LoginActivity;
import com.example.smartflowerpot.Activity.RegisterActivity;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.ViewModel.PlantsOverviewViewModel;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CreateFragment extends Fragment {
    private EditText nicknameField;
    private EditText deviceIdentifierField;
    private Button createPlantbtn;
    private View view;
    private DatePicker datePicker;
    private PlantsOverviewViewModel plantsOverviewViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).setTopbarTitle("New plant");
    }

    private void createPlant() {
        int day = datePicker.getDayOfMonth() + 1;
        int month = datePicker.getMonth();
        int year = datePicker.getYear() - 1900;

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date(year, month, day, 0,0,0));
        System.out.println(nowAsISO);



        Plant plant = new Plant( nowAsISO , nicknameField.getText().toString(), deviceIdentifierField.getText().toString());
        System.out.println(plant.toString());
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
        datePicker = view.findViewById(R.id.datePicker);

        plantsOverviewViewModel = new ViewModelProvider(this).get(PlantsOverviewViewModel.class);


        createPlantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPlant();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        plantsOverviewViewModel.getPlantsResponse();
        super.onResume();
    }
}