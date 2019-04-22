package com.softserve.tips4trips.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.softserve.tips4trips.database.entities.Account;

import java.util.Date;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;



@Dao
public interface AccountDao {

    @Insert(onConflict = REPLACE)
    void save(Account account);

    @Query("SELECT * FROM account WHERE id = :accountId")
    LiveData<Account> load(String accountId);

    @Query("SELECT * FROM account WHERE id = :accountId AND lastRefresh > :lastRefreshMax LIMIT 1")
    Account hasAccount(String accountId, Date lastRefreshMax);
}