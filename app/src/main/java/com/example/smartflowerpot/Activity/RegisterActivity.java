package com.example.smartflowerpot.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private TextInputEditText usernameInputRegister;
    private TextInputEditText passwordInputRegister;
    private DatePicker dobDatePicker;
    private Spinner regionSpinner;
    private RadioGroup genderRadioGroup;
    private AccountViewModel accountViewModel;

    private String[] gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity);

        initViews();

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegister();
            }
        });

        gender = new String[1];
        genderRadioGroup.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == R.id.radioBtnMale) {
                gender[0] = "MALE";
            } else if (i == R.id.radioBtnFemale) {
                gender[0] = "FEMALE";
            } else if (i == R.id.radioBtnOther) {
                gender[0] = "OTHER";
            }
        }));
    }

    private void initViews() {
        registerButton = findViewById(R.id.registerButton);
        usernameInputRegister = findViewById(R.id.usernameInputRegister);
        passwordInputRegister = findViewById(R.id.passwordInputRegister);
        dobDatePicker = findViewById(R.id.dobDatePicker);
        regionSpinner = findViewById(R.id.regionSpinner);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);

        populateRegion();
    }

    private void populateRegion() {
        List<String> regions = new ArrayList<>();
        regions.add("North America");
        regions.add("South America");
        regions.add("Europe");
        regions.add("Asia");
        regions.add("Australia");
        regions.add("Africa");

        ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, regions);

        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        regionSpinner.setAdapter(regionAdapter);
    }

    private void handleRegister() {
        String username = usernameInputRegister.getText().toString().trim();
        String password = passwordInputRegister.getText().toString().trim();

        int day = dobDatePicker.getDayOfMonth() + 1;
        int month = dobDatePicker.getMonth();
        int year = dobDatePicker.getYear() - 1900;

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date(year, month, day, 0, 0, 0));
        System.out.println(nowAsISO);


        String region = regionSpinner.getSelectedItem().toString();


        if (username.isEmpty()) {
            usernameInputRegister.setError("Username cannot be empty.");
            usernameInputRegister.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordInputRegister.setError("Password cannot be empty.");
            passwordInputRegister.requestFocus();
            return;
        }

        LiveData<Account> account = accountViewModel.registerAccount(username, password, nowAsISO, gender[0], region);

        account.observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                if (account != null) {
                    accountViewModel.persistLoggedInUser(account.getUsername());
                    Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, BaseActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
