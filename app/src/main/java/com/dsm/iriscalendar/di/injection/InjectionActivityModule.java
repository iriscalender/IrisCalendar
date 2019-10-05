package com.dsm.iriscalendar.di.injection;

import com.dsm.iriscalendar.di.module.login.LoginModule;
import com.dsm.iriscalendar.di.module.signUp.SignUpModule;
import com.dsm.iriscalendar.di.scope.LoginActivityScope;
import com.dsm.iriscalendar.di.scope.SignUpActivityScope;
import com.dsm.iriscalendar.ui.login.LoginActivity;
import com.dsm.iriscalendar.ui.signUp.SignUpActivity;

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
}
