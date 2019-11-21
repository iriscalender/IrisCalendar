package com.dsm.iriscalendar;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.MockitoAnnotations;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public class BaseTest {
    protected HttpException createHttpException() {
        return new HttpException(
                Response.error(
                        500,
                        ResponseBody.create(
                                "",
                                MediaType.parse("application/json")
                        )
                )
        );
    }

    protected HttpException createHttpException(int errorCode) {
        return new HttpException(
                Response.error(
                        errorCode,
                        ResponseBody.create(
                                "",
                                MediaType.parse("application/json")
                        )
                )
        );
    }

    protected HttpException createHttpException(int errorCode, String json) {
        return new HttpException(
                Response.error(
                        errorCode,
                        ResponseBody.create(
                                json,
                                MediaType.parse("application/json; charset=utf-8")
                        )
                )
        );
    }

    @Rule public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }
}