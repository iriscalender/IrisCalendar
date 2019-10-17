package com.dsm.iriscalendar.ui.addFixedSchedule;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.data.model.FixedSchedule;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

public class AddFixedScheduleRepository implements AddFixedScheduleContract.Repository {

    private Api api;

    public AddFixedScheduleRepository(Api api) {
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
    public Flowable<Response<FixedSchedule>> addFixedSchedule(String category, String todo, String startTime, String endTime) {
        Map<String, String> params = new HashMap<>();
        params.put("category", category);
        params.put("calendarName", todo);
        params.put("startTime", startTime);
        params.put("endTime", endTime);

        return api.addFixedSchedule(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
