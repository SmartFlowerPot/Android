package com.example.smartflowerpot.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private TextInputEditText usernameInputRegister;
    private TextInputEditText passwordInputRegister;
    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity);
        registerButton = findViewById(R.id.registerButton);
        usernameInputRegister = findViewById(R.id.usernameInputRegister);
        passwordInputRegister = findViewById(R.id.passwordInputRegister);

        Context context = getApplicationContext();
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInputRegister.getText().toString();
                String password = passwordInputRegister.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast toast = Toast.makeText(context, "Fields cannot be empty!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    LiveData<Account> account = accountViewModel.registerAccount(username, password);
                    if (account.getValue() != null) {
                        Toast toast = Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(RegisterActivity.this, BaseActivity.class);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });
    }


}
