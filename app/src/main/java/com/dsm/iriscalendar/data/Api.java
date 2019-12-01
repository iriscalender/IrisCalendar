package com.dsm.iriscalendar.data;

import com.dsm.iriscalendar.data.model.AuthResponse;
import com.dsm.iriscalendar.data.model.CalendarBookList;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.data.model.FixedSchedule;
import com.dsm.iriscalendar.data.model.Schedule;
import com.dsm.iriscalendar.data.model.ScheduleResponse;
import com.dsm.iriscalendar.data.model.TimeResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @POST("auth/signin")
    Flowable<Response<AuthResponse>> login(@Body Object params);

    @POST("auth/signup")
    Flowable<Response<AuthResponse>> signUp(@Body Object params);

    @POST("time")
    Flowable<Response<Object>> timeSet(@Body Object params);

    @GET("time")
    Flowable<Response<TimeResponse>> getTimeSet();

    @PATCH("time")
    Flowable<Response<Object>> updateTimeSet(@Body Object params);

    @GET("category")
    Flowable<Response<Category>> getCategory();

    @PATCH("category")
    Flowable<Response<Category>> modifyCategory(@Body Category params);

    @POST("calendar/auto")
    Flowable<Response<Schedule>> addSchedule(@Body Object params);

    @POST("calendar/manual")
    Flowable<Response<FixedSchedule>> addFixedSchedule(@Body Object params);

    @GET("calendar/book")
    Flowable<CalendarBookList> getCalendarBook();

    @GET("calendar/{date}")
    Flowable<ScheduleResponse> getCalendarSchedule(@Path("date") String date);

    @GET("calendar/manual/{id}")
    Flowable<CalendarSchedule> getSchedule(@Path("id") int id);

    @PATCH("calendar/manual/{id}")
    Flowable<Response<CalendarSchedule>> modifySchedule(
            @Path("id") int id,
            @Body Object params
    );

    @DELETE("/calendar/auto/{id}")
    Flowable<Response<Void>> completeAuto(@Path("id") int id);

    @DELETE("/calendar/manual/{id}")
    Flowable<Response<Void>> completeManual(@Path("id") int id);
}
