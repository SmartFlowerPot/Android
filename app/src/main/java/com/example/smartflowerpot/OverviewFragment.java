package com.example.smartflowerpot;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.ViewModel.CO2ViewModel;

public class OverviewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    CO2 currentCO2;
    TextView co2Level;
    CO2ViewModel co2ViewModel;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public OverviewFragment() {
    }

    public static OverviewFragment newInstance(String param1, String param2) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        co2ViewModel = new ViewModelProvider(this).get(CO2ViewModel.class);
        co2ViewModel.getCO2().observe(this, new Observer<CO2>() {
            @Override
            public void onChanged(CO2 co2) {
                currentCO2 = co2ViewModel.getCO2().getValue();


                co2Level.setText("CO2 Level: " + currentCO2.getcO2level() + " ppm");
            }
        });


    }

    @Override
    public void onResume() {
        //TODO RETRIEVE EUI AND PUT IT INSIDE REQUEST
        // co2ViewModel.getCO2Request();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        co2Level = view.findViewById(R.id.co2Level);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onRefresh() {
        //TODO RETRIEVE EUI AND PUT IT INSIDE REQUEST
        // co2ViewModel.getCO2Request();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}