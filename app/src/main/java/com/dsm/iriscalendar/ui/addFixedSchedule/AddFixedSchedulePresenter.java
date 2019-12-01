package com.dsm.iriscalendar.ui.addFixedSchedule;

import android.util.Log;

import com.dsm.iriscalendar.base.BasePresenter;

import kr.sdusb.libs.messagebus.MessageBus;

public class AddFixedSchedulePresenter
        extends BasePresenter<AddFixedScheduleContract.View>
        implements AddFixedScheduleContract.Presenter {

    private AddFixedScheduleContract.Repository repository;

    public AddFixedSchedulePresenter(AddFixedScheduleContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void getCategory() {
        addDisposable(
                repository.getCategory().subscribe(
                        response -> view.setCategory(response),
                        throwable -> view.toastServerError()
                )
        );
    }

    @Override
    public void addFixedSchedule() {
        String category = view.getCategory();
        String todo = view.getTodo();
        String startTime = view.getStartTime();
        String endTime = view.getEndTime();

        Log.d("DEBUGLOG", "category:"+category + "todo:" + todo +"start:"+startTime + "end" + endTime);

        if (category.isEmpty() || todo.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            view.toastBlankError();
            return;
        }

        addDisposable(
                repository.addFixedSchedule(
                        category,
                        todo,
                        startTime,
                        endTime
                ).subscribe(response -> {
                    if (response.code() == 201) {
                        view.finishActivity();
                        MessageBus.getInstance().handle(0, null);
                    } else if (response.code() == 409) {
                        view.toastImpossibleSchedule();
                    } else {
                        view.toastServerError();
                    }
                }, throwable -> view.toastServerError())
        );
    }
}
