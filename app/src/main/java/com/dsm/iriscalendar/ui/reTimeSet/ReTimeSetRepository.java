package com.dsm.iriscalendar.ui.reTimeSet;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.data.model.TimeResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

public class ReTimeSetRepository implements ReTimeSetContract.Repository {

    private Api api;
    private PrefHelper prefHelper;

    public ReTimeSetRepository(Api api, PrefHelper prefHelper) {
        this.api = api;
        this.prefHelper = prefHelper;
    }

    @Override
    public Flowable<TimeResponse> getTimeSet() {
        return api.getTimeSet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response.code() != 200) throw new HttpException(response);
                    return response.body();
                });
    }

    @Override
    public Flowable<Response<Object>> updateTimeSet(String startTime, String endTime) {
        Map<String, String> params = new HashMap<>();
        params.put("start", startTime);
        params.put("end", endTime);
        return api.updateTimeSet(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
