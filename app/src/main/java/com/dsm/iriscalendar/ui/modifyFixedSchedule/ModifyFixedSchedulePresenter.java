package com.dsm.iriscalendar.ui.modifyFixedSchedule;

import com.dsm.iriscalendar.base.BasePresenter;

public class ModifyFixedSchedulePresenter extends BasePresenter<ModifyFixedScheduleContract.View> implements ModifyFixedScheduleContract.Presenter {

    private ModifyFixedScheduleContract.Repository repository;

    public ModifyFixedSchedulePresenter(ModifyFixedScheduleContract.Repository repository) {
        this.repository = repository;
    }
}
