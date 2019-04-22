package com.softserve.tips4trips.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.softserve.tips4trips.database.converter.DateConverter;
import com.softserve.tips4trips.database.dao.AccountDao;
import com.softserve.tips4trips.database.entities.Account;


@Database(entities = {Account.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {

    private static volatile MyDatabase INSTANCE;

    public abstract AccountDao accountDao();
}
