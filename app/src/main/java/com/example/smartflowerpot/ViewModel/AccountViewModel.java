package com.example.smartflowerpot.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.Repository.AccountRepo;
import com.example.smartflowerpot.Repository.TemperatureRepo;

public class AccountViewModel extends ViewModel {
    private static AccountRepo accountRepo;

    public AccountViewModel() {
        accountRepo = AccountRepo.getInstance();
    }

    public LiveData<Account> getAccount(String username, String password) {

        if(username == "" || password == ""){
            return null;
        }
        else {
            return accountRepo.getAccount(username, password);
        }

    }
    public LiveData<Account> registerAccount(String username, String password){
        return accountRepo.registerAccount(username, password);
    }
}
