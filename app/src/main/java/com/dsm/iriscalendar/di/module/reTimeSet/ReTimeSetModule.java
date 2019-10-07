package com.dsm.iriscalendar.di.module.reTimeSet;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.di.scope.ReTimeSetActivityScope;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetContract;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetPresenter;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ReTimeSetModule {

    @ReTimeSetActivityScope
    @Provides
    ReTimeSetContract.Repository provideReTimeSetRepository(Api api, PrefHelper prefHelper) {
        return new ReTimeSetRepository(api, prefHelper);
    }

    @ReTimeSetActivityScope
    @Provides
    ReTimeSetContract.Presenter provideReTimeSetPresenter(ReTimeSetContract.Repository repository) {
        return new ReTimeSetPresenter(repository);
    }
}
