package com.dsm.iriscalendar.ui.login;

import com.dsm.iriscalendar.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginContract.Repository repository;

    public LoginPresenter(LoginContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void login() {
        String id = view.getId();
        String password = view.getPassword();

        if (id.isEmpty() || password.isEmpty()) {
            view.toastBlankError();
            return;
        }

        if (id.length() < 6) {
            view.toastShortId();
            return;
        }

        addDisposable(
                repository.login(id, password).subscribe(it -> {
                    switch (it.code()) {
                        case 200:
                            view.finishActivity();
                            view.startMainActivity();
                            break;
                        case 400:
                            view.toastInvalidValue();
                            break;
                        default:
                            view.toastServerError();
                    }
                })
        );
    }
}
