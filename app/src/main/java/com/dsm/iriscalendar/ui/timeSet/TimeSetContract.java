package com.dsm.iriscalendar.ui.timeSet;

import com.dsm.iriscalendar.base.BaseContract;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface TimeSetContract {
    interface View extends BaseContract.View {
        String getStartTime();
        String getEndTime();

        void toastStartTimeFast();
        void toastSameTime();
        void toastServerError();
        void toastInvalidValue();

        void startMainActivity();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void timeSet();
    }

    interface Repository {
        Flowable<Response<Object>> timeSet(String startTime, String endTime);
    }
}
