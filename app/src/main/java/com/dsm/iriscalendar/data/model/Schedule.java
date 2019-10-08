package com.dsm.iriscalendar.data.model;

public class Schedule {

    private Boolean isAutomatic;
    private String category;
    private String calenderName;
    private String endTime;
    private Integer requiredTime;
    private Boolean isParticularImportant;

    public boolean isAutomatic() {
        return isAutomatic;
    }

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

    public Schedule(Boolean isAutomatic, String category, String calenderName, String endTime, Integer requiredTime, Boolean isParticularImportant) {
        this.isAutomatic = isAutomatic;
        this.category = category;
        this.calenderName = calenderName;
        this.endTime = endTime;
        this.requiredTime = requiredTime;
        this.isParticularImportant = isParticularImportant;
    }
}
