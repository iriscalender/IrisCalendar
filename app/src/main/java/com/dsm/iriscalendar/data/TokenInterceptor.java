package com.dsm.iriscalendar.data;

import com.dsm.iriscalendar.data.local.PrefHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private PrefHelper prefHelper;

    public TokenInterceptor(PrefHelper prefHelper) {
        this.prefHelper = prefHelper;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", prefHelper.getToken())
                .build();

        return chain.proceed(request);
    }
}
