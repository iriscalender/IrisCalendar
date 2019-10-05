package com.dsm.iriscalendar.ui.signUp;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.local.PrefHelper;
import com.dsm.iriscalendar.data.model.AuthResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SignUpRepository implements SignUpContract.Repository {

    private Api api;
    private PrefHelper prefHelper;

    public SignUpRepository(Api api, PrefHelper prefHelper) {
        this.api = api;
        this.prefHelper = prefHelper;
    }

    @Override
    public Flowable<Response<AuthResponse>> signUp(String id, String password, String reType) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("password1", password);
        params.put("password2", reType);
        return api.signUp(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(response -> {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            AuthResponse body = response.body();
                            prefHelper.saveUuid(body.getUuid());
                            prefHelper.saveToken(body.getToken());
                        }
                    }
                });
    }
}
