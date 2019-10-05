package com.dsm.iriscalendar.ui.timeSet;

import com.dsm.iriscalendar.base.BasePresenter;

public class TimeSetPresenter extends BasePresenter<TimeSetContract.View> implements TimeSetContract.Presenter {

    private TimeSetContract.Repository repository;

    public TimeSetPresenter(TimeSetContract.Repository repository) {
        this.repository = repository;
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
                repository.timeSet(startTime, endTime).subscribe(response -> {
                    switch (response.code()) {
                        case 200:
                            view.startMainActivity();
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
