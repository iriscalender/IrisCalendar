package com.dsm.iriscalendar.ui.modifyFixedSchedule;

import com.dsm.iriscalendar.base.BasePresenter;

public class ModifyFixedSchedulePresenter extends BasePresenter<ModifyFixedScheduleContract.View> implements ModifyFixedScheduleContract.Presenter {

    private ModifyFixedScheduleContract.Repository repository;

    public ModifyFixedSchedulePresenter(ModifyFixedScheduleContract.Repository repository) {
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
    public void getFixedSchedule() {

    }
}
