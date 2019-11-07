package com.dsm.iriscalendar.ui.modifySchedule;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.data.model.Category;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface ModifyScheduleContract {
    interface View extends BaseContract.View {
        void setCategory(Category category);

        void setSchedule(CalendarSchedule schedule);

        void toastServerError();

        void toastInvalidValue();

        void finishActivity();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCategory();

        void getSchedule(int id);

        void modifySchedule(int id, String category, String calendarName, String startTime, String endTime);
    }

    interface Repository {
        Flowable<Category> getCategory();

        Flowable<CalendarSchedule> getSchedule(int id);

        Flowable<Response<CalendarSchedule>> modifySchedule(int id, String category, String calendarName, String startTime, String endTime);
    }
}
