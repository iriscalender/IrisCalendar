package com.dsm.iriscalendar.di.injection;

import com.dsm.iriscalendar.di.module.addFixedSchedule.AddFixedScheduleModule;
import com.dsm.iriscalendar.di.module.addSchedule.AddScheduleModule;
import com.dsm.iriscalendar.di.module.category.CategoryModule;
import com.dsm.iriscalendar.di.module.login.LoginModule;
import com.dsm.iriscalendar.di.module.main.MainModule;
import com.dsm.iriscalendar.di.module.modifyFixedSchedule.ModifyFixedScheduleModule;
import com.dsm.iriscalendar.di.module.modifySchedule.ModifyScheduleModule;
import com.dsm.iriscalendar.di.module.reTimeSet.ReTimeSetModule;
import com.dsm.iriscalendar.di.module.signUp.SignUpModule;
import com.dsm.iriscalendar.di.module.timeSet.TimeSetModule;
import com.dsm.iriscalendar.di.scope.AddFixedScheduleActivityScope;
import com.dsm.iriscalendar.di.scope.AddScheduleActivityScope;
import com.dsm.iriscalendar.di.scope.CategoryActivityScope;
import com.dsm.iriscalendar.di.scope.LoginActivityScope;
import com.dsm.iriscalendar.di.scope.MainActivityScope;
import com.dsm.iriscalendar.di.scope.ModifyFixedScheduleActivityScope;
import com.dsm.iriscalendar.di.scope.ModifyScheduleActivityScope;
import com.dsm.iriscalendar.di.scope.ReTimeSetActivityScope;
import com.dsm.iriscalendar.di.scope.SignUpActivityScope;
import com.dsm.iriscalendar.di.scope.TimeSetActivityScope;
import com.dsm.iriscalendar.ui.main.MainActivity;
import com.dsm.iriscalendar.ui.addFixedSchedule.AddFixedScheduleActivity;
import com.dsm.iriscalendar.ui.addSchedule.AddScheduleActivity;
import com.dsm.iriscalendar.ui.category.CategoryActivity;
import com.dsm.iriscalendar.ui.login.LoginActivity;
import com.dsm.iriscalendar.ui.modifyFixedSchedule.ModifyFixedScheduleActivity;
import com.dsm.iriscalendar.ui.modifySchedule.ModifyScheduleActivity;
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

    @CategoryActivityScope
    @ContributesAndroidInjector(modules = CategoryModule.class)
    abstract CategoryActivity activityCategory();

    @AddScheduleActivityScope
    @ContributesAndroidInjector(modules = AddScheduleModule.class)
    abstract AddScheduleActivity activityAddSchedule();

    @MainActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity activityMain();

    @AddFixedScheduleActivityScope
    @ContributesAndroidInjector(modules = AddFixedScheduleModule.class)
    abstract AddFixedScheduleActivity activityAddFixedSchedule();

    @ModifyScheduleActivityScope
    @ContributesAndroidInjector(modules = ModifyScheduleModule.class)
    abstract ModifyScheduleActivity activityModifySchedule();

    @ModifyFixedScheduleActivityScope
    @ContributesAndroidInjector(modules = ModifyFixedScheduleModule.class)
    abstract ModifyFixedScheduleActivity activityModifyFixedSchedule();
}
