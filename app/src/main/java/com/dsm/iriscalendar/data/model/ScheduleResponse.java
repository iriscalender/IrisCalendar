package com.dsm.iriscalendar.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResponse {

    @SerializedName("manual")
    List<CalendarSchedule> manualSchedule;

    @SerializedName("auto")
    List<CalendarSchedule> autoSchedule;

    public List<CalendarSchedule> getManualSchedule() {
        return manualSchedule;
    }

    public List<CalendarSchedule> getAutoSchedule() {
        return autoSchedule;
    }
}
