package com.example.smartflowerpot.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.example.smartflowerpot.ViewModel.TemperatureViewModel;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;
    private TextView printTest;
    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        loginButton = findViewById(R.id.loginButton1);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        printTest = findViewById(R.id.printTest);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                printTest.setText(accountViewModel.getAccount(username, password).getValue().getUsername());

            }
        });
    }




}
