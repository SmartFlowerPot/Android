package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Activity.LoginActivity;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;

public class AccountFragment extends Fragment {

    private Button logoutbtn;

    private AccountViewModel accountViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        ((BaseActivity)getActivity()).setTopbarTitle("Your account");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        initViews(view);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogout();
            }
        });

        return view;
    }

    private void handleLogout() {
        accountViewModel.discontinueLoggedInUser(accountViewModel.getPersistedLoggedInUser());
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    private void initViews(View view) {
        logoutbtn = view.findViewById(R.id.logoutBtn);
    }
}