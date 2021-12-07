package com.example.smartflowerpot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Adapters.PlantsAdapter;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.example.smartflowerpot.ViewModel.PlantsOverviewViewModel;
import com.example.smartflowerpot.ViewModel.TemperatureViewModel;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment implements PlantsAdapter.OnListItemClickListener{
    private View view;
    private RecyclerView recycledViewPlants;
    private PlantsAdapter plantsAdapter;
    private PlantsOverviewViewModel plantsOverviewViewModel;
    private AccountViewModel accountViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //((BaseActivity)getActivity()).setTopbarTitle("Your plants"); why this doesnt work?????????????????????????????????????????????????????
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_overview, container, false);

        recycledViewPlants = view.findViewById(R.id.recycledViewPlants);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recycledViewPlants.setLayoutManager(gridLayoutManager);

        plantsOverviewViewModel = new ViewModelProvider(this).get(PlantsOverviewViewModel.class);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        accountViewModel.getPlants("karlo");

        //plantsOverviewViewModel.getPlants("karlo");

        plantsOverviewViewModel.getPlantsResponse().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(List<Plant> plants) {
                plantsAdapter.setmPlants(plants);
            }
        });

        ArrayList<Plant> plants = new ArrayList<>();
        plants.add(new Plant("123", "sadasdasd", null));
        plants.add(new Plant("345", "asdasdas", null));
        plants.add(new Plant("567", "fghjfghj", null));
        plants.add(new Plant("789", "fg", null));
        plants.add(new Plant("91011", "sfgshs", null));

        plantsAdapter = new PlantsAdapter(plants, this);
        recycledViewPlants.setAdapter(plantsAdapter);
        return view;
    }

    @Override
    public void onListItemClick(String deviceIdentifier) {
        Bundle bundle = new Bundle();
        bundle.putString("DeviceIdentifier", deviceIdentifier);
        Navigation.findNavController(view).navigate(R.id.action_overviewFragment_to_plant, bundle);
    }
}