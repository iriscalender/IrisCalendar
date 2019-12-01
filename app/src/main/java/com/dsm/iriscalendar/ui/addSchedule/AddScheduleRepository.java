package com.dsm.iriscalendar.ui.addSchedule;

import android.util.Log;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.data.model.Schedule;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

public class AddScheduleRepository implements AddScheduleContract.Repository {

    private Api api;

    public AddScheduleRepository(Api api) {
        this.api = api;
    }

    @Override
    public Flowable<Response<Schedule>> addSchedule(String category, String todo, String endTime, int requirementTime, boolean isParticularImportant) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("calendarName", todo);
        params.put("endTime", endTime);
        params.put("requiredTime", requirementTime);
        params.put("isParticularImportant", isParticularImportant);

        Log.d("DEBUGLOG", "add schedule param" + params.toString());

        return api.addSchedule(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
}
