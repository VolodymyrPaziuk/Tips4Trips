package com.softserve.tips4trips.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.softserve.tips4trips.database.entities.Account;
import com.softserve.tips4trips.repositories.AccountRepository;

import javax.inject.Inject;

public class AccountProfileViewModel extends ViewModel {

    private LiveData<Account> account;
    private AccountRepository accountRepository;

    @Inject
    public AccountProfileViewModel(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void init(String accountId) {
        if (this.account != null) {
            return;
        }
        account = accountRepository.getAccount(accountId);
    }

    public LiveData<Account> getaccount() {
        return this.account;
    }
}
