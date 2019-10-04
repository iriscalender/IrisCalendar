package com.dsm.iriscalendar.ui.login;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.LoginResponse;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface LoginContract {
    interface Presenter extends BaseContract.Presenter<View> {
        void login();
    }

    interface View extends BaseContract.View {
        String getId();
        String getPassword();

        void toastBlankError();
        void toastShortId();
        void toastServerError();
        void toastInvalidValue();

        void finishActivity();
        void startMainActivity();
    }

    interface Repository {
        Flowable<Response<LoginResponse>> login(String id, String password);
    }
}
