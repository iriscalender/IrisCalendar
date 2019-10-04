package com.dsm.iriscalendar.data;

import com.dsm.iriscalendar.data.model.LoginResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("auth/login")
    Flowable<Response<LoginResponse>> login(@Body Object params);


}
