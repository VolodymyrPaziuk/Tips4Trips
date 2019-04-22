package com.softserve.tips4trips.repositories;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import android.widget.Toast;

import com.softserve.tips4trips.App;
import com.softserve.tips4trips.api.AccountWebservice;
import com.softserve.tips4trips.database.dao.AccountDao;
import com.softserve.tips4trips.database.entities.Account;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



@Singleton
public class AccountRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final AccountWebservice webservice;
    private final AccountDao accountDao;
    private final Executor executor;

    @Inject
    public AccountRepository(AccountWebservice webservice, AccountDao accountDao, Executor executor) {
        this.webservice = webservice;
        this.accountDao = accountDao;
        this.executor = executor;
    }


    public LiveData<Account> getAccount(String accountId) {
        refreshUser(accountId);
        return accountDao.load(accountId);
    }

    private void refreshUser(final String accountId) {
        executor.execute(() -> {
            // Check if user was fetched recently
            boolean userExists = (accountDao.hasAccount(accountId, getMaxRefreshTime(new Date())) != null);
            // If account have to be updated
            if (!userExists) {
                webservice.getAccount(accountId).enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        Log.e("TAG", "DATA REFRESHED FROM NETWORK");
                        Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            Account account = response.body();
                            account.setLastRefresh(new Date());
                            accountDao.save(account);
                        });
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) { }
                });
            }
        });
    }

    // ---

    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
