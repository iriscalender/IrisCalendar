package com.dsm.iriscalendar.ui.addSchedule;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.data.model.Schedule;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface AddScheduleContract {
    interface View extends BaseContract.View {
        String getCategory();

        String getTodo();

        String getEndTime();

        int getRequiredTime();

        boolean getIsParticularImportant();

        void toastBlankError();

        void toastServerError();

        void toastInvalidValue();

        void toastImpossibleSchedule();

        void finishActivity();

        void setCategory(Category category);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void addSchedule();

        void getCategory();
    }

    interface Repository {
        Flowable<Response<Schedule>> addSchedule(
                String category,
                String todo,
                String endTime,
                int requirementTime,
                boolean isParticularImportant
        );

        Flowable<Category> getCategory();
    }
}
