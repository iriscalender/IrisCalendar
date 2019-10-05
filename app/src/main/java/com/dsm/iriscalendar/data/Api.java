package com.dsm.iriscalendar.data;

import com.dsm.iriscalendar.data.model.AuthResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("auth/login")
    Flowable<Response<AuthResponse>> login(@Body Object params);

    @POST("auth/signup")
    Flowable<Response<AuthResponse>> signUp(@Body Object params);

    @POST("time")
    Flowable<Response<Object>> timeSet(@Body Object params);
}
