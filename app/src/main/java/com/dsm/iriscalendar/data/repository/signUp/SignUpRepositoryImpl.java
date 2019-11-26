package com.dsm.iriscalendar.data.repository.signUp;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.data.model.AuthResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SignUpRepositoryImpl implements SignUpRepository {

    private Api api;
    private PrefHelper prefHelper;

    public SignUpRepositoryImpl(Api api, PrefHelper prefHelper) {
        this.api = api;
        this.prefHelper = prefHelper;
    }

    @Override
    public Flowable<Integer> signUp(String id, String password, String reType) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("password1", password);
        params.put("password2", reType);
        return api.signUp(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(response -> {
                    if (response.code() == 200 && response.body() != null) {
                        AuthResponse body = response.body();
                        prefHelper.saveToken(body.getToken());
                    }
                })
                .map(Response::code);
    }
}
