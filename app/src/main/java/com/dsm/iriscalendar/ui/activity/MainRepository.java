package com.dsm.iriscalendar.ui.activity;

import com.dsm.iriscalendar.data.Api;

public class MainRepository implements MainContract.Repository {

    private Api api;

    public MainRepository(Api api) {
        this.api = api;
    }
}
