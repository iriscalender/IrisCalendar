package com.dsm.iriscalendar.data.repository.main;

import com.dsm.iriscalendar.data.model.CalendarBook;
import com.dsm.iriscalendar.data.model.MappedCalendarSchedule;

import java.util.List;

import io.reactivex.Flowable;

public interface MainRepository {
    Flowable<List<CalendarBook>> getCalendarBook();

    Flowable<List<MappedCalendarSchedule>> getCalendarSchedule(String date);
}
