package com.dsm.iriscalendar.ui.addSchedule;

import android.util.Log;

import com.dsm.iriscalendar.base.BasePresenter;

import java.util.Objects;

import kr.sdusb.libs.messagebus.MessageBus;

public class AddSchedulePresenter extends BasePresenter<AddScheduleContract.View> implements AddScheduleContract.Presenter {

    private AddScheduleContract.Repository repository;

    public AddSchedulePresenter(AddScheduleContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void addSchedule() {
        String category = view.getCategory();
        String todo = view.getTodo();
        String endTime = view.getEndTime();
        int requirementTime = view.getRequiredTime();
        boolean isParticularImportant = view.getIsParticularImportant();

        if (category.isEmpty() || todo.isEmpty() || endTime.isEmpty() || requirementTime == 0) {
            view.toastBlankError();
            return;
        }

        Log.d("DEBUGLOG", "category:" + category + "todo:" + todo + "end" + endTime);

        addDisposable(
                repository.addSchedule(category, todo, endTime, requirementTime, isParticularImportant)
                        .subscribe(response -> {
                            switch (response.code()) {
                                case 201:
                                    view.finishActivity();
                                    MessageBus.getInstance().handle(0, null);
                                    break;
                                case 400:
                                    view.toastInvalidValue();
                                    break;
                                case 409:
                                    view.toastImpossibleSchedule();
                                    break;
                                default:
                                    Log.d("DEBUGLOG", "add schedule" + response.code());
                                    view.toastServerError();
                            }
                        }, throwable -> {
                            view.toastServerError();
                            Log.d("DEBUGLOG", "add Schedule" + Objects.requireNonNull(throwable.getMessage()));
                        })
        );
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
}
