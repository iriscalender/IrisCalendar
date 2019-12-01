package com.dsm.iriscalendar.ui.timeSet;

import android.util.Log;

import com.dsm.iriscalendar.data.Api;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TimeSetRepository implements TimeSetContract.Repository {

    private Api api;

    public TimeSetRepository(Api api) {
        this.api = api;
    }

    @Override
    public Flowable<Response<Object>> timeSet(String startTime, String endTime) {
        Map<String, String> params = new HashMap<>();
        params.put("start", startTime);
        params.put("end", endTime);
        Log.d("DEBUGLOG", startTime + " " + endTime);
        return api.timeSet(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
