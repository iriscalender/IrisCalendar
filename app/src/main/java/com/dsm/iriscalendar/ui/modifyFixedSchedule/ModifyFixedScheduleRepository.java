package com.dsm.iriscalendar.ui.modifyFixedSchedule;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.model.Category;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ModifyFixedScheduleRepository implements ModifyFixedScheduleContract.Repository {

    private Api api;

    public ModifyFixedScheduleRepository(Api api) {
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
}
