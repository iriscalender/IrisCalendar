package com.dsm.iriscalendar.ui.modifySchedule;

import com.dsm.iriscalendar.base.BasePresenter;

public class ModifySchedulePresenter extends BasePresenter<ModifyScheduleContract.View> implements ModifyScheduleContract.Presenter {

    private ModifyScheduleContract.Repository repository;

    public ModifySchedulePresenter(ModifyScheduleContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void getCategory() {
        addDisposable(
                repository.getCategory()
                        .subscribe(
                                response -> view.setCategory(response),
                                throwable -> view.toastServerError()
                        )
        );
    }

    @Override
    public void getSchedule(int id) {
        addDisposable(
                repository.getSchedule(id)
                        .subscribe(
                                response -> view.setSchedule(response),
                                throwable -> view.toastServerError()
                        )
        );
    }

    @Override
    public void modifySchedule(int id, String category, String calendarName, String startTime, String endTime) {
        addDisposable(
                repository.modifySchedule(id, category, calendarName, startTime, endTime)
                        .subscribe(response -> {
                            switch (response.code())  {
                                case 200:
                                    view.finishActivity();
                                    break;
                                case 400:
                                    view.toastInvalidValue();
                                    break;
                            }
                        }, throwable -> view.toastServerError())
        );
    }
}
