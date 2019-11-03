package com.dsm.iriscalendar.ui.activity;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.CalendarBook;
import com.dsm.iriscalendar.data.model.CalendarSchedule;

import java.util.List;

import io.reactivex.Flowable;

public interface MainContract {
    interface View extends BaseContract.View {
        void toastServerError();

        void setCalendarBook(List<CalendarBook> calendarBook);

        void setCalendarSchedule(List<CalendarSchedule> calendarSchedule);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCalendarBook();

        void getCalendarSchedule(String date);
    }

    interface Repository {
        Flowable<List<CalendarBook>> getCalendarBook();

        Flowable<List<CalendarSchedule>> getCalendarSchedule(String date);
    }
}
