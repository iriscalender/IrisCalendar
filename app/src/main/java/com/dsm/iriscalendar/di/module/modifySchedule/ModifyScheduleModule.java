package com.dsm.iriscalendar.di.module.modifySchedule;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.di.scope.ModifyScheduleActivityScope;
import com.dsm.iriscalendar.ui.modifySchedule.ModifyScheduleContract;
import com.dsm.iriscalendar.ui.modifySchedule.ModifySchedulePresenter;
import com.dsm.iriscalendar.ui.modifySchedule.ModifyScheduleRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ModifyScheduleModule {

    @ModifyScheduleActivityScope
    @Provides
    ModifyScheduleContract.Repository provideModifyScheduleRepository(Api api) {
        return new ModifyScheduleRepository(api);
    }

    @ModifyScheduleActivityScope
    @Provides
    ModifyScheduleContract.Presenter provideModifySchedulePresenter(ModifyScheduleContract.Repository repository) {
        return new ModifySchedulePresenter(repository);
    }
}
