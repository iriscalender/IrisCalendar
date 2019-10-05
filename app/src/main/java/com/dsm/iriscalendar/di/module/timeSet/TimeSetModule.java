package com.dsm.iriscalendar.di.module.timeSet;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.di.scope.TimeSetActivityScope;
import com.dsm.iriscalendar.ui.timeSet.TimeSetContract;
import com.dsm.iriscalendar.ui.timeSet.TimeSetPresenter;
import com.dsm.iriscalendar.ui.timeSet.TimeSetRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class TimeSetModule {

    @TimeSetActivityScope
    @Provides
    TimeSetContract.Repository provideTimeSetRepository(Api api) {
        return new TimeSetRepository(api);
    }

    @TimeSetActivityScope
    @Provides
    TimeSetContract.Presenter provideTimeSetPresenter(TimeSetContract.Repository repository) {
        return new TimeSetPresenter(repository);
    }
}
