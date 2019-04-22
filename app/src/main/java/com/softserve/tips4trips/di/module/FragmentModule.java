package com.softserve.tips4trips.di.module;


import com.softserve.tips4trips.fragments.AccountProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract AccountProfileFragment contributAccountProfileFragment();
}
