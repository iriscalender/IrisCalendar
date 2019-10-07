package com.dsm.iriscalendar.di.injection;

import com.dsm.iriscalendar.di.module.login.LoginModule;
import com.dsm.iriscalendar.di.module.reTimeSet.ReTimeSetModule;
import com.dsm.iriscalendar.di.module.signUp.SignUpModule;
import com.dsm.iriscalendar.di.module.timeSet.TimeSetModule;
import com.dsm.iriscalendar.di.scope.LoginActivityScope;
import com.dsm.iriscalendar.di.scope.ReTimeSetActivityScope;
import com.dsm.iriscalendar.di.scope.SignUpActivityScope;
import com.dsm.iriscalendar.di.scope.TimeSetActivityScope;
import com.dsm.iriscalendar.ui.login.LoginActivity;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetActivity;
import com.dsm.iriscalendar.ui.signUp.SignUpActivity;
import com.dsm.iriscalendar.ui.timeSet.TimeSetActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectionActivityModule {

    @LoginActivityScope
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity activityLogin();

    @SignUpActivityScope
    @ContributesAndroidInjector(modules = SignUpModule.class)
    abstract SignUpActivity activitySignUp();

    @TimeSetActivityScope
    @ContributesAndroidInjector(modules = TimeSetModule.class)
    abstract TimeSetActivity activityTimeSet();

    @ReTimeSetActivityScope
    @ContributesAndroidInjector(modules = ReTimeSetModule.class)
    abstract ReTimeSetActivity activityReTimeSet();
}
