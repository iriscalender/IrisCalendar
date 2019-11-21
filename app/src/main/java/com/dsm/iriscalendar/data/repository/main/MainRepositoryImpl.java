package com.dsm.iriscalendar.data.repository.main;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.model.CalendarBook;
import com.dsm.iriscalendar.data.model.CalendarSchedule;

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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<List<CalendarSchedule>> getCalendarSchedule(String date) {
        return api.getCalendarSchedule(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
