package com.dsm.iriscalendar.di.module.addFixedSchedule;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.di.scope.AddFixedScheduleActivityScope;
import com.dsm.iriscalendar.ui.addFixedSchedule.AddFixedScheduleContract;
import com.dsm.iriscalendar.ui.addFixedSchedule.AddFixedSchedulePresenter;
import com.dsm.iriscalendar.ui.addFixedSchedule.AddFixedScheduleRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class AddFixedScheduleModule {

    @Provides
    @AddFixedScheduleActivityScope
    AddFixedScheduleContract.Repository provideAddFixedScheduleRepository(Api api) {
        return new AddFixedScheduleRepository(api);
    }

    @Provides
    @AddFixedScheduleActivityScope
    AddFixedScheduleContract.Presenter provideAddFixedSchedulePresenter(AddFixedScheduleContract.Repository repository) {
        return new AddFixedSchedulePresenter(repository);
    }
}
