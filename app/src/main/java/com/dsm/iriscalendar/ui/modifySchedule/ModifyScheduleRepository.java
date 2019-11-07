package com.dsm.iriscalendar.ui.modifySchedule;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.data.model.Category;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

public class ModifyScheduleRepository implements ModifyScheduleContract.Repository {

    private Api api;

    public ModifyScheduleRepository(Api api) {
        this.api = api;
    }

    @Override
    public Flowable<Category> getCategory() {
        return api.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response.code() != 200) throw new HttpException(response);
                    return response.body();
                });
    }

    @Override
    public Flowable<CalendarSchedule> getSchedule(int id) {
        return api.getSchedule(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<Response<CalendarSchedule>> modifySchedule(int id, String category, String calendarName, String startTime, String endTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("calendarName", calendarName);
        params.put("startTime", startTime);
        params.put("endTime", endTime);

        return api.modifySchedule(id, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
