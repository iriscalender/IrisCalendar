package com.dsm.iriscalendar.di.module;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.di.scope.LoginActivityScope;
import com.dsm.iriscalendar.ui.login.LoginContract;
import com.dsm.iriscalendar.ui.login.LoginPresenter;
import com.dsm.iriscalendar.ui.login.LoginRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @LoginActivityScope
    @Provides
    LoginContract.Repository provideLoginRepository(Api api, PrefHelper prefHelper) {
        return new LoginRepository(api, prefHelper);
    }

    @Provides
    @LoginActivityScope
    LoginContract.Presenter providePresenter(LoginContract.Repository repository) {
        return new LoginPresenter(repository);
    }
}
