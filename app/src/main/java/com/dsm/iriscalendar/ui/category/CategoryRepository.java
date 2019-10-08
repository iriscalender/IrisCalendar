package com.dsm.iriscalendar.ui.category;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.model.Category;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

public class CategoryRepository implements CategoryContract.Repository {

    private Api api;

    public CategoryRepository(Api api) {
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
    public Flowable<Response<Category>> modifyCategory(Category category) {
        return api.modifyCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
