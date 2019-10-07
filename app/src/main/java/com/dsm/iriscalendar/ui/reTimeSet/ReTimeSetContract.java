package com.dsm.iriscalendar.ui.reTimeSet;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.TimeResponse;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface ReTimeSetContract {
    interface View extends BaseContract.View {
        void setStartTime(String startTime);
        void setEndTime(String endTime);

        String getStartTime();
        String getEndTime();

        void toastSameTime();
        void toastServerError();
        void toastStartTimeFast();
        void toastInvalidValue();

        void finishActivity();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getTimeSet();
        void timeSet();
    }

    interface Repository {
        Flowable<TimeResponse> getTimeSet();
        Flowable<Response<Object>> updateTimeSet(String startTime, String endTime);
    }
}
