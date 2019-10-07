package com.dsm.iriscalendar.data.model;

public class TimeResponse {

    private String startTime;
    private String endTime;

    public TimeResponse(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
