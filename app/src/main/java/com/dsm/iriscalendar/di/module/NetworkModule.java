package com.dsm.iriscalendar.di.module;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.TokenInterceptor;
import com.dsm.iriscalendar.data.local.PrefHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(PrefHelper prefHelper) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new TokenInterceptor(prefHelper))
                .build();
    }

    @Provides
    @Singleton
    Api provideApi(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("http://iriscalendar.ap-northeast-2.elasticbeanstalk.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(Api.class);
    }
}
