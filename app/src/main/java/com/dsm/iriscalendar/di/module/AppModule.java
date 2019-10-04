package com.dsm.iriscalendar.di.module;

import android.app.Application;
import android.content.Context;

import com.dsm.iriscalendar.IrisCalendarApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class, LocalModule.class})
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(IrisCalendarApplication application) {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication(IrisCalendarApplication application) {
        return application;
    }
}
