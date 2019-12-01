package com.dsm.iriscalendar.data.repository.login;

import io.reactivex.Flowable;

public interface LoginRepository {

    Flowable<Integer> login(String id, String password);
}
