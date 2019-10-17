package com.dsm.iriscalendar.di.module.main;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.di.scope.MainActivityScope;
import com.dsm.iriscalendar.ui.activity.MainContract;
import com.dsm.iriscalendar.ui.activity.MainPresenter;
import com.dsm.iriscalendar.ui.activity.MainRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @MainActivityScope
    MainContract.Repository provideMainRepository(Api api) {
        return new MainRepository(api);
    }

    @Provides
    @MainActivityScope
    MainContract.Presenter provideMainPresenter(MainContract.Repository repository) {
        return new MainPresenter(repository);
    }
}
