package com.dsm.iriscalendar.data.repository.main;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.model.CalendarBook;
import com.dsm.iriscalendar.data.model.CalendarBookList;
import com.dsm.iriscalendar.data.model.MappedCalendarSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainRepositoryImpl implements MainRepository {

    private Api api;

    public MainRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Flowable<List<CalendarBook>> getCalendarBook() {
        return api.getCalendarBook()
                .map(CalendarBookList::getCalendarBooks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Flowable<List<MappedCalendarSchedule>> getCalendarSchedule(String date) {
        return api.getCalendarSchedule(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(value -> {
                    List<MappedCalendarSchedule> list = new ArrayList<>();
                    value.getAutoSchedule().forEach(e -> list.add(new MappedCalendarSchedule(
                            e.getId(),
                            e.getCategory(),
                            e.getCalendarName(),
                            e.getStartTime(),
                            e.getEndTime(),
                            true
                    )));
                    value.getManualSchedule().forEach(e -> list.add(new MappedCalendarSchedule(
                            e.getId(),
                            e.getCategory(),
                            e.getCalendarName(),
                            e.getStartTime(),
                            e.getEndTime(),
                            false
                    )));

                    Collections.sort(list, (o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));

                    return list;
                });
    }
}
