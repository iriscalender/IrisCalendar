package com.dsm.iriscalendar.ui.login;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.data.model.LoginResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginRepository implements LoginContract.Repository {

    private Api api;
    private PrefHelper prefHelper;

    public LoginRepository(Api api, PrefHelper prefHelper) {
        this.api = api;
        this.prefHelper = prefHelper;
    }

    @Override
    public Flowable<Response<LoginResponse>> login(String id, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);
        return api.login(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(it -> {
                    if (it.code() == 200) {
                        if (it.body() != null) {
                            LoginResponse response = it.body();
                            prefHelper.saveToken(response.getToken());
                            prefHelper.saveUuid(response.getUuid());
                        }
                    }
                });
    }
}
