package com.dsm.iriscalendar.di.module;

import android.content.Context;

import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.data.local.PrefHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalModule {

    @Provides
    @Singleton
    PrefHelper providePrefHelper(Context context) {
        return new PrefHelperImpl(context);
    }
}
