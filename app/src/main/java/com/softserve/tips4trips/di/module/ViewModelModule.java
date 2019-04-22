package com.softserve.tips4trips.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.softserve.tips4trips.di.key.ViewModelKey;
import com.softserve.tips4trips.view_models.AccountProfileViewModel;
import com.softserve.tips4trips.view_models.FactoryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountProfileViewModel.class)
    abstract ViewModel bindAccountProfileViewModel(AccountProfileViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
