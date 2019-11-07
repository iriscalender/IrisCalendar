package com.dsm.iriscalendar.ui.modifyFixedSchedule;

import com.dsm.iriscalendar.data.Api;

public class ModifyFixedScheduleRepository implements ModifyFixedScheduleContract.Repository {

    private Api api;

    public ModifyFixedScheduleRepository(Api api) {
        this.api = api;
    }
}
