package com.softserve.tips4trips.di.component;

import android.app.Application;

import com.softserve.tips4trips.App;
import com.softserve.tips4trips.di.module.ActivityModule;
import com.softserve.tips4trips.di.module.AppModule;
import com.softserve.tips4trips.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules={AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
