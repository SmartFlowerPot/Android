package com.example.smartflowerpot.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Adapters.PlantsAdapter;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;


import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment implements PlantsAdapter.OnListItemClickListener{
    private View view;
    private RecyclerView recycledViewPlants;
    private PlantsAdapter plantsAdapter;
    private AccountViewModel accountViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_overview, container, false);

        initViews();

        getViewModels();

        accountViewModel.getPlants("karlo");

        plantsAdapter = new PlantsAdapter(new ArrayList<>(), this);
        recycledViewPlants.setAdapter(plantsAdapter);

        accountViewModel.getPlantsResponse().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(List<Plant> plants) {
                plantsAdapter.setmPlants(plants);
                recycledViewPlants.setAdapter(plantsAdapter);
            }
        });

        return view;
    }

    private void initViews() {
        recycledViewPlants = view.findViewById(R.id.recycledViewPlants);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycledViewPlants.setLayoutManager(layoutManager);
    }

    private void getViewModels() {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity)getActivity()).setTopbarTitle("Your plants");
    }

    @Override
    public void onListItemClick(String deviceIdentifier) {
        Bundle bundle = new Bundle();
        bundle.putString("DeviceIdentifier", deviceIdentifier);
        Navigation.findNavController(view).navigate(R.id.action_overviewFragment_to_plant, bundle);
    }
}