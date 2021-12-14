package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.example.smartflowerpot.utils.Utils;
import com.example.smartflowerpot.ViewModel.PlantViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CreateFragment extends Fragment {
    private EditText nicknameField;
    private EditText deviceIdentifierField;
    private EditText plantTypeField;
    private TextView onlineMessage;
    private Button createPlantbtn;

    private View view;
    private PlantViewModel plantViewModel;
    private AccountViewModel accountViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).setTopbarTitle("New plant");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create, container, false);


        //----------Ionut----------
        initViews();

        getViewModels();
        //-------------------------


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
    private void createPlant() {
        //----------Ionut----------

        String plantDob = Utils.getTodayAsString();

        String nicknameInput = nicknameField.getText().toString().trim();
        if (nicknameInput.isEmpty()){
            nicknameField.setError("Please input a nickname for your plant");
            nicknameField.requestFocus();
            return;
        }
        String deviceIdentifierInput = deviceIdentifierField.getText().toString().trim();
        if (deviceIdentifierInput.isEmpty()){
            deviceIdentifierField.setError("Please input your device identifier");
            deviceIdentifierField.requestFocus();
            return;
        }
        String plantTypeInput = plantTypeField.getText().toString().trim();
        if (plantTypeInput.isEmpty()){
            plantTypeField.setError("Please input your plant type");
            plantTypeField.requestFocus();
            return;
        }
        Plant plant = new Plant(plantDob, nicknameInput, deviceIdentifierInput, plantTypeInput);

        if(Utils.isNetworkAvailable(getActivity())) {
            plantViewModel.createAPlant(accountViewModel.getPersistedLoggedInUser(), plant);
            deviceIdentifierField.setText("");
            nicknameField.setText("");
            plantTypeField.setText("");
            Navigation.findNavController(view).navigate(R.id.overviewFragment);
        } else{
            Toast.makeText(getActivity(), "Cannot create plant. Please connect to internet.", Toast.LENGTH_SHORT).show();
        }

        //-------------------------
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

    //----------Ionut----------

    private void getViewModels() {
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }
    private void initViews(){
        nicknameField = view.findViewById(R.id.nicknameField);
        deviceIdentifierField = view.findViewById(R.id.deviceIdentifierField);
        plantTypeField = view.findViewById(R.id.typeField);
        createPlantbtn = view.findViewById(R.id.createPlantBtn);
        onlineMessage = view.findViewById(R.id.onlineMessage);
        onlineMessage.setVisibility(View.INVISIBLE);
    }

    //-------------------------

}