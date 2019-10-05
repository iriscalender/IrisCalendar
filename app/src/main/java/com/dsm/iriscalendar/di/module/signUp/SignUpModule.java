package com.dsm.iriscalendar.di.module.signUp;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.di.scope.SignUpActivityScope;
import com.dsm.iriscalendar.ui.signUp.SignUpContract;
import com.dsm.iriscalendar.ui.signUp.SignUpPresenter;
import com.dsm.iriscalendar.ui.signUp.SignUpRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpModule {

    @SignUpActivityScope
    @Provides
    SignUpContract.Repository provideSignUpRepository(Api api, PrefHelper prefHelper) {
        return new SignUpRepository(api, prefHelper);
    }

    @SignUpActivityScope
    @Provides
    SignUpContract.Presenter provideSignUpPresenter(SignUpContract.Repository repository) {
        return new SignUpPresenter(repository);
    }
}
