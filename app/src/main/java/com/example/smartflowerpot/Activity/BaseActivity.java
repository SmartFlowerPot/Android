package com.example.smartflowerpot.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.TemperatureViewModel;

import java.text.SimpleDateFormat;


public class BaseActivity extends AppCompatActivity {
    private Button update;
    private TextView temperature;
    private TextView timeStamp;
    private TemperatureViewModel temperatureViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseactivity);
        update = findViewById(R.id.button);
        temperature = findViewById(R.id.temperatureTextView);
        timeStamp = findViewById(R.id.timeStampTextView);


        temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);
        temperatureViewModel.getTemperature();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeStampFormatted = new SimpleDateFormat("MM/dd - HH:mm:ss").format(temperatureViewModel.getTemperature().getValue().getTimeStamp());
                temperature.setText("Temperature: " + Double.toString(temperatureViewModel.getTemperature().getValue().getTemperature()) + "°C");
                timeStamp.setText(timeStampFormatted);
            }

        });


    }
}