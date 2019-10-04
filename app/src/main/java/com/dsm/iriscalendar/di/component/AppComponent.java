package com.dsm.iriscalendar.di.component;

import com.dsm.iriscalendar.IrisCalendarApplication;
import com.dsm.iriscalendar.di.injection.InjectionActivityModule;
import com.dsm.iriscalendar.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class, InjectionActivityModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<IrisCalendarApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(IrisCalendarApplication application);

        AppComponent build();
    }
}
