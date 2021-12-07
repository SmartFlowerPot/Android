package com.example.smartflowerpot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.ViewModel.HumidityViewModel;

public class OverviewFragment extends Fragment {

    private HumidityViewModel humidityViewModel;
    private TextView textView;
    private Button button;

    public OverviewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        humidityViewModel = new ViewModelProvider(this).get(HumidityViewModel.class);

        humidityViewModel.getHumidity().observe(this, new Observer<Humidity>() {
            @Override
            public void onChanged(Humidity humidity) {
                textView.setText(humidity.toString());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                humidityViewModel.getHumidityRequest();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        textView = view.findViewById(R.id.overviewText);
        button = view.findViewById(R.id.overviewButton);

        return view;

    }
}