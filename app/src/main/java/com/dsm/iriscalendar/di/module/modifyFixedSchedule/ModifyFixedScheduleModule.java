package com.dsm.iriscalendar.di.module.modifyFixedSchedule;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.di.scope.ModifyFixedScheduleActivityScope;
import com.dsm.iriscalendar.ui.modifyFixedSchedule.ModifyFixedScheduleContract;
import com.dsm.iriscalendar.ui.modifyFixedSchedule.ModifyFixedSchedulePresenter;
import com.dsm.iriscalendar.ui.modifyFixedSchedule.ModifyFixedScheduleRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ModifyFixedScheduleModule {

    @ModifyFixedScheduleActivityScope
    @Provides
    ModifyFixedScheduleContract.Repository provideModifyFixedScheduleRepository(Api api) {
        return new ModifyFixedScheduleRepository(api);
    }

    @ModifyFixedScheduleActivityScope
    @Provides
    ModifyFixedScheduleContract.Presenter provideModifyFixedSchedulePresenter(ModifyFixedScheduleContract.Repository repository) {
        return new ModifyFixedSchedulePresenter(repository);
    }
}
