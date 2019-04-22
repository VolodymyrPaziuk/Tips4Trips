package com.softserve.tips4trips.api;

import com.softserve.tips4trips.database.entities.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountWebservice {



    @GET("/accounts/{account}")
    Call<Account> getAccount(@Path("account") String accountId);
}
