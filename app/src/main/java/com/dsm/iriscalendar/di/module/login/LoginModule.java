package com.dsm.iriscalendar.di.module.login;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.data.repository.login.LoginRepository;
import com.dsm.iriscalendar.data.repository.login.LoginRepositoryImpl;
import com.dsm.iriscalendar.di.scope.LoginActivityScope;
import com.dsm.iriscalendar.ui.login.LoginViewModel;
import com.dsm.iriscalendar.ui.login.LoginViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @LoginActivityScope
    @Provides
    LoginRepository provideLoginRepository(Api api, PrefHelper prefHelper) {
        return new LoginRepositoryImpl(api, prefHelper);
    }

    @Provides
    @LoginActivityScope
    LoginViewModelFactory provideLoginViewModelFactory(LoginRepository loginRepository) {
        return new LoginViewModelFactory(loginRepository);
    }

    @Provides
    @LoginActivityScope
    LoginViewModel provideLoginViewModel(LoginViewModelFactory loginViewModelFactory) {
        return loginViewModelFactory.create(LoginViewModel.class);
    }
}
