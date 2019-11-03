package com.dsm.iriscalendar.data.model;

public class CalendarSchedule {

    private int id;
    private String category;
    private String calendarName;
    private String startTime;
    private String endTime;
    private boolean isAuto;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public boolean isAuto() {
        return isAuto;
    }
}
