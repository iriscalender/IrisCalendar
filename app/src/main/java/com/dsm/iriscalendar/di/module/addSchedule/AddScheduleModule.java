package com.dsm.iriscalendar.di.module.addSchedule;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.di.scope.AddScheduleActivityScope;
import com.dsm.iriscalendar.ui.addSchedule.AddScheduleContract;
import com.dsm.iriscalendar.ui.addSchedule.AddSchedulePresenter;
import com.dsm.iriscalendar.ui.addSchedule.AddScheduleRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class AddScheduleModule {

    @AddScheduleActivityScope
    @Provides
    AddScheduleContract.Repository provideAddScheduleRepository(Api api) {
        return new AddScheduleRepository(api);
    }

    @AddScheduleActivityScope
    @Provides
    AddScheduleContract.Presenter provideAddSchedulePresenter(AddScheduleContract.Repository repository) {
        return new AddSchedulePresenter(repository);
    }
}
