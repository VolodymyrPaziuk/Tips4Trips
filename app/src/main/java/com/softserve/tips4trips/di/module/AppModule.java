package com.softserve.tips4trips.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softserve.tips4trips.api.AccountWebservice;
import com.softserve.tips4trips.database.MyDatabase;
import com.softserve.tips4trips.database.dao.AccountDao;
import com.softserve.tips4trips.repositories.AccountRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public class AppModule {


    @Provides
    @Singleton
    MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                MyDatabase.class, "MyDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    AccountDao providAccountDao(MyDatabase database) { return database.accountDao(); }


    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    AccountRepository provideAccountRepository(AccountWebservice webservice, AccountDao accountDao, Executor executor) {
        return new AccountRepository(webservice, accountDao, executor);
    }

    private static String BASE_URL = "http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    AccountWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(AccountWebservice.class);
    }
}
