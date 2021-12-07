package com.example.smartflowerpot.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private Button goToRegisterButton;
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;
    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        initViews();

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        goToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
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
                    Toast toast = Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, BaseActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
