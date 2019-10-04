package com.dsm.iriscalendar.di.injection;

import com.dsm.iriscalendar.di.module.LoginModule;
import com.dsm.iriscalendar.di.scope.LoginActivityScope;
import com.dsm.iriscalendar.ui.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectionActivityModule {

    @LoginActivityScope
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity activityLogin();
}
