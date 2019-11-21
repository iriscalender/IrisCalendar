package com.dsm.iriscalendar.di.module.signUp;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.data.repository.signUp.SignUpRepository;
import com.dsm.iriscalendar.data.repository.signUp.SignUpRepositoryImpl;
import com.dsm.iriscalendar.di.scope.SignUpActivityScope;
import com.dsm.iriscalendar.ui.signUp.SignUpViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpModule {

    @SignUpActivityScope
    @Provides
    SignUpRepository provideSignUpRepository(Api api, PrefHelper prefHelper) {
        return new SignUpRepositoryImpl(api, prefHelper);
    }

    @SignUpActivityScope
    @Provides
    SignUpViewModel provideSignUpViewModel(SignUpRepository repository) {
        return new SignUpViewModel(repository);
    }
}
