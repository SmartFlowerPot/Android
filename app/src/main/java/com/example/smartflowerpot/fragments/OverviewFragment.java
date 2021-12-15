package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Adapters.DeleteAdapter;
import com.example.smartflowerpot.Adapters.PlantsAdapter;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.utils.Utils;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.example.smartflowerpot.ViewModel.PlantViewModel;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment implements PlantsAdapter.OnListItemClickListener{
    private View view;
    private RecyclerView recycledViewPlants;
    private PlantsAdapter plantsAdapter;
    private AccountViewModel accountViewModel;
    private PlantViewModel plantViewModel;
    private ImageView deleteImage;
    private DeleteAdapter deleteAdapter;
    //  private int currentimage = R.drawable.ic_icons_delete;

    private ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_overview, container, false);

        initViews();

        getViewModels();

        plantsAdapter = new PlantsAdapter(new ArrayList<>(), this);

        recycledViewPlants.setAdapter(plantsAdapter);

        updatePlants();


        return view;

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void updatePlants(){
        if (Utils.isNetworkAvailable(getActivity())){
            plantViewModel.getPlantsFromAPI(accountViewModel.getPersistedLoggedInUser());

            plantViewModel.getPlantsResponseFromAPI().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
                @Override
                public void onChanged(List<Plant> plants) {
                    progressBar.setVisibility(View.GONE);
                    plantsAdapter.setmPlants(plants);
                    recycledViewPlants.setAdapter(plantsAdapter);
                }
            });
        } else {
            System.out.println("Gotten plants from db instead");

            plantViewModel.getPlantsResponseFromDb().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
                @Override
                public void onChanged(List<Plant> plants) {
                    plantsAdapter.setmPlants(plants);
                    recycledViewPlants.setAdapter(plantsAdapter);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity)getActivity()).setTopbarTitle("Your plants");

        updatePlants();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onListItemClick(String deviceIdentifier) {
        if (Utils.isNetworkAvailable(getActivity())){
            Bundle bundle = new Bundle();
            bundle.putString("DeviceIdentifier", deviceIdentifier);
            Navigation.findNavController(view).navigate(R.id.action_overviewFragment_to_plant, bundle);
        } else {
            Toast.makeText(getContext(), "Connect to internet in order to get more info", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListDeleteItemClick(String eui) {
        plantViewModel.deletePlant(eui);
    }

    private void initViews() {

        recycledViewPlants = view.findViewById(R.id.recycledViewPlants);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycledViewPlants.setLayoutManager(layoutManager);

        progressBar = view.findViewById(R.id.plantsOverviewPB);
        deleteImage = view.findViewById(R.id.imageView2);

    }

    private void getViewModels() {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
    }

    public void delete(String eui){
        plantViewModel.deletePlant("");
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
/*
    public void delete(){
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentimage == R.drawable.ic_window_close_) {

                    plantViewModel.deletePlant("0004A30B00251001");


                }
            }
        });
    }

 */
}