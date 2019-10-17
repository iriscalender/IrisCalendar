package com.dsm.iriscalendar.data.model;

public class Schedule {

    private String category;
    private String calenderName;
    private String endTime;
    private Integer requiredTime;
    private Boolean isParticularImportant;

    public String getCategory() {
        return category;
    }

    public String getCalenderName() {
        return calenderName;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getRequiredTime() {
        return requiredTime;
    }

    public boolean isParticularImportant() {
        return isParticularImportant;
    }

    public Schedule() {

    }

    public Schedule(String category, String calenderName, String endTime, Integer requiredTime, Boolean isParticularImportant) {
        this.category = category;
        this.calenderName = calenderName;
        this.endTime = endTime;
        this.requiredTime = requiredTime;
        this.isParticularImportant = isParticularImportant;
    }
}
