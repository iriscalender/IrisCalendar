package com.dsm.iriscalendar.ui.addFixedSchedule;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.data.model.FixedSchedule;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface AddFixedScheduleContract {
    interface View extends BaseContract.View {
        String getCategory();

        String getTodo();

        String getStartTime();

        String getEndTime();

        void setCategory(Category category);

        void toastServerError();

        void toastBlankError();

        void toastImpossibleSchedule();

        void finishActivity();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCategory();

        void addFixedSchedule();
    }

    interface Repository {
        Flowable<Category> getCategory();

        Flowable<Response<FixedSchedule>> addFixedSchedule(
                String category,
                String todo,
                String startTime,
                String endTime
        );
    }
}
