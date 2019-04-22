package com.softserve.tips4trips.di.module;


import com.softserve.tips4trips.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;



@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
