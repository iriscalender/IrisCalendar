package com.dsm.iriscalendar.di.module.main;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.repository.main.MainRepository;
import com.dsm.iriscalendar.data.repository.main.MainRepositoryImpl;
import com.dsm.iriscalendar.di.scope.MainActivityScope;
import com.dsm.iriscalendar.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @MainActivityScope
    MainRepository provideMainRepository(Api api) {
        return new MainRepositoryImpl(api);
    }

    @Provides
    @MainActivityScope
    MainViewModel provideMainPresenter(MainRepository repository) {
        return new MainViewModel(repository);
    }
}
