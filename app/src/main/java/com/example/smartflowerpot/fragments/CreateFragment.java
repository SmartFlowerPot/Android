package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.Utils;
import com.example.smartflowerpot.ViewModel.PlantViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CreateFragment extends Fragment {
    private EditText nicknameField;
    private EditText deviceIdentifierField;
    private TextView onlineMessage;
    private Button createPlantbtn;
    private View view;
    private PlantViewModel plantViewModel;
    private SharedPreferences sharedPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        ((BaseActivity) getActivity()).setTopbarTitle("New plant");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createPlant() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);

        Plant plant = new Plant(nowAsISO, nicknameField.getText().toString(), deviceIdentifierField.getText().toString());

        if(Utils.isNetworkAvailable(getActivity())) {
            plantViewModel.createAPlant(sharedPreferences.getString("username","noUsername"), plant);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create, container, false);

        nicknameField = view.findViewById(R.id.nicknameField);
        deviceIdentifierField = view.findViewById(R.id.deviceIdentifierField);
        createPlantbtn = view.findViewById(R.id.createPlantBtn);
        onlineMessage = view.findViewById(R.id.onlineMessage);
        onlineMessage.setVisibility(View.INVISIBLE);

        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);

        createPlantbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                createPlant();
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        if(Utils.isNetworkAvailable(getActivity())) {
            onlineMessage.setVisibility(View.INVISIBLE);
        } else {
            onlineMessage.setVisibility(View.VISIBLE);
            onlineMessage.setText("To create a plant, please connect to internet.");
        }
    }
}