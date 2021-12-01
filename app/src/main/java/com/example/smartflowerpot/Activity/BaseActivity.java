package com.example.smartflowerpot.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;
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

        initViews();

        temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);
        temperatureViewModel.getTemperature().observe(this, new Observer<Temperature>() {
            @Override
            public void onChanged(Temperature temperature) {
                if(temperature != null) {
                    //String timeStampFormatted = new SimpleDateFormat("MM/dd - HH:mm:ss").format(temperature.getTimeStamp());
                    BaseActivity.this.temperature.setText("Temperature: " + Double.toString(temperature.getTemperature()) + "°C");
                    timeStamp.setText(temperature.getTimeStamp());
                }
                else Toast.makeText(getApplicationContext(), "Problem with getting temperature.", Toast.LENGTH_LONG).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temperatureViewModel.getTemperatureRequest();
            }
        });
    }

    private void initViews() {
        update = findViewById(R.id.button);
        temperature = findViewById(R.id.temperatureTextView);
        timeStamp = findViewById(R.id.timeStampTextView);
    }
}