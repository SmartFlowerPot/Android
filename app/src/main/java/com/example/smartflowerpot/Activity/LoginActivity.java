package com.example.smartflowerpot.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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

        loginButton = findViewById(R.id.loginButton1);
        goToRegisterButton = findViewById(R.id.goToRegisterButton);
        usernameInput = findViewById(R.id.usernameInputRegister);
        passwordInput = findViewById(R.id.passwordInputRegister);

        Context context = getApplicationContext();


        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                System.out.println(password);
                if (username.isEmpty() || password.isEmpty()) {
                    Toast toast = Toast.makeText(context, "Fields cannot be empty!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    LiveData<Account> account = accountViewModel.getAccount(username, password);
                    if (account == null) {
                        Toast toast = Toast.makeText(context, "User not found", Toast.LENGTH_SHORT);
                        toast.show();

                    } else {
                        Intent intent = new Intent(LoginActivity.this, BaseActivity.class);
                        startActivity(intent);
                    }
                }

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
}
