package com.dsm.iriscalendar.ui.signUp;

import com.dsm.iriscalendar.base.BasePresenter;

public class SignUpPresenter extends BasePresenter<SignUpContract.View> implements SignUpContract.Presenter {

    private SignUpContract.Repository repository;

    public SignUpPresenter(SignUpContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void signUp() {
        String id = view.getInputId();
        String password = view.getInputPassword();
        String reType = view.getInputReType();

        if (id.isEmpty() || password.isEmpty() || reType.isEmpty()) {
            view.toastBlankError();
            return;
        }

        if (id.length() < 6) {
            view.toastShortId();
            return;
        }

        if (password.length() < 8) {
            view.toastShortPassword();
            return;
        }

        if (!password.equals(reType)) {
            view.toastNotSamePassword();
            return;
        }

        addDisposable(
                repository.signUp(id, password, reType).subscribe(it -> {
                    switch (it.code()) {
                        case 200:
                            view.startMainActivity();
                            break;
                        case 400:
                            view.toastInvalidValue();
                            break;
                        case 409:
                            view.toastUserAlreadyExists();
                            break;
                        default:
                            view.toastServerError();
                    }
                }, throwable -> view.toastServerError())
        );

    }
}
