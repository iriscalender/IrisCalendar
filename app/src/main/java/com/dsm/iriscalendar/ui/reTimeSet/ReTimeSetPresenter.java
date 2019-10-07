package com.dsm.iriscalendar.ui.reTimeSet;

import com.dsm.iriscalendar.base.BasePresenter;

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
                    view.setEndTime(response.getEndTime());
                }, throwable -> view.toastServerError())
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

        int startTimeHour = Integer.parseInt(startTime.split(":")[0]);
        int endTimeHour = Integer.parseInt(endTime.split(":")[0]);
        int startTimeMinute = Integer.parseInt(startTime.split(":")[1]);
        int endTimeMinute = Integer.parseInt(endTime.split(":")[1]);
        if (startTimeHour > endTimeHour || ((startTimeHour == endTimeHour) && startTimeMinute > endTimeMinute)) {
            view.toastStartTimeFast();
            return;
        }

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
                    }
                }, throwable -> view.toastServerError())
        );
    }
}
