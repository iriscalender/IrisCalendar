package com.dsm.iriscalendar.data.model;

public class MappedCalendarSchedule {

    private int id;
    private String category;
    private String calendarName;
    private String startTime;
    private String endTime;
    private boolean isAuto;

    public MappedCalendarSchedule(int id, String category, String calendarName, String startTime, String endTime, boolean isAuto) {
        this.id = id;
        this.category = category;
        this.calendarName = calendarName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAuto = isAuto;
    }

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
