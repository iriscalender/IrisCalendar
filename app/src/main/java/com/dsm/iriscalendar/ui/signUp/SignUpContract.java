package com.dsm.iriscalendar.ui.signUp;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.AuthResponse;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface SignUpContract {
    interface View extends BaseContract.View {
        String getInputId();
        String getInputPassword();
        String getInputReType();

        void toastBlankError();
        void toastShortId();
        void toastShortPassword();
        void toastNotSamePassword();
        void toastServerError();
        void toastInvalidValue();
        void toastUserAlreadyExists();

        void startMainActivity();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void signUp();
    }

    interface Repository {
        Flowable<Response<AuthResponse>> signUp(String id, String password, String reType);
    }
}
