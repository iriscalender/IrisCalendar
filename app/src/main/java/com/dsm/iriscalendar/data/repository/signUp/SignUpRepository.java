package com.dsm.iriscalendar.data.repository.signUp;

import io.reactivex.Flowable;

public interface SignUpRepository {
    Flowable<Integer> signUp(String id, String password, String reType);
}
