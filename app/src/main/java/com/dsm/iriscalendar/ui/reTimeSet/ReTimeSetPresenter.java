package com.dsm.iriscalendar.ui.reTimeSet;

import android.util.Log;

import com.dsm.iriscalendar.base.BasePresenter;

import retrofit2.HttpException;

public class ReTimeSetPresenter extends BasePresenter<ReTimeSetContract.View> implements ReTimeSetContract.Presenter {

    private ReTimeSetContract.Repository repository;

    public ReTimeSetPresenter(ReTimeSetContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void getTimeSet() {
        addDisposable(
                repository.getTimeSet().subscribe(response -> {
                    view.setStartTime(response.getStartTime());
                    view.setEndTime(response.getEnd());
                }, throwable -> {
                    view.toastServerError();
                    Log.d("DEBUGLOG", ((HttpException) throwable).message());
                    Log.d("DEBUGLOG", "getTimeSet" + throwable.getMessage());
                })
        );
    }

    @Override
    public void timeSet() {
        String startTime = view.getStartTime();
        String endTime = view.getEndTime();


        if (startTime.equals(endTime)) {
            view.toastSameTime();
            return;
        }

//        int startTimeHour = Integer.parseInt(startTime.split(":")[0]);
//        int endTimeHour = Integer.parseInt(endTime.split(":")[0]);
//        int startTimeMinute = Integer.parseInt(startTime.split(":")[1]);
//        int endTimeMinute = Integer.parseInt(endTime.split(":")[1]);
//        if (startTimeHour > endTimeHour || ((startTimeHour == endTimeHour) && startTimeMinute > endTimeMinute)) {
//            view.toastStartTimeFast();
//            return;
//        }

        Log.d("DEBUGLOG", startTime + " " + endTime);

        addDisposable(
                repository.updateTimeSet(startTime, endTime).subscribe(response -> {
                    switch (response.code()) {
                        case 200:
                            view.finishActivity();
                            break;
                        case 400:
                            view.toastInvalidValue();
                            break;
                        default:
                            view.toastServerError();
                            Log.d("DEBUGLOG", "timeSet" + response.code());
                    }
                }, throwable -> view.toastServerError())
        );
    }
}
