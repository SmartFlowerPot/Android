package com.example.smartflowerpot.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.example.smartflowerpot.ViewModel.PlantViewModel;
import com.example.smartflowerpot.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private Button goToRegisterButton;
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;

    private AccountViewModel accountViewModel;
    private PlantViewModel plantViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        initViews();
        initializeViewModels();

        String loggedInUser = accountViewModel.getPersistedLoggedInUser();

        if(loggedInUser != null) {
            startActivity(new Intent(LoginActivity.this, BaseActivity.class));
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        goToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()){
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No internet connection. Please connect to internet.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initializeViewModels() {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
    }

    private void initViews() {
        loginButton = findViewById(R.id.loginButton1);
        goToRegisterButton = findViewById(R.id.goToRegisterButton);
        usernameInput = findViewById(R.id.usernameInputRegister);
        passwordInput = findViewById(R.id.passwordInputRegister);
    }

    private void handleLogin(){
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (username.isEmpty()){
            usernameInput.setError("Username cannot be empty.");
            usernameInput.requestFocus();
            return;
        }

        if (password.isEmpty()){
            passwordInput.setError("Password cannot be empty");
            passwordInput.requestFocus();
            return;
        }

        LiveData<Account> accountLiveData = accountViewModel.getAccount(username, password);

        accountLiveData.observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                if (account == null) {
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                } else {
                    accountViewModel.persistLoggedInUser(account.getUsername());
                    plantViewModel.deleteAllPlantsFromDb();

                    startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
