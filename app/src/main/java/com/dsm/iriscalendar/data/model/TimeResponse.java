package com.dsm.iriscalendar.data.model;

public class TimeResponse {

    private String start;
    private String end;

    public TimeResponse(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStartTime() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
