package com.example.smartflowerpot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.example.smartflowerpot.ViewModel.TemperatureViewModel;

public class BaseActivity extends AppCompatActivity {
    private Button update;
    private TextView textView;
    private TemperatureViewModel temperatureViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseactivity);
        update = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LiveData<Temperature> temperature = temperatureViewModel.getTemperature();
                textView.setText(temperature.getValue().toString());
            }

        });


    }
}