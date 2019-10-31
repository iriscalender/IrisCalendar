package com.dsm.iriscalendar.ui.activity;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.CalendarBook;

import java.util.List;

import io.reactivex.Flowable;

public interface MainContract {
    interface View extends BaseContract.View {
        void toastServerError();

        void setCalendarBook(List<CalendarBook> calendarBook);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCalendarBook();
    }

    interface Repository {
        Flowable<List<CalendarBook>> getCalendarBook();
    }
}
