package com.dsm.iriscalendar.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalendarBookList {

    @SerializedName("calendar")
    private List<CalendarBook> calendarBooks;

    public List<CalendarBook> getCalendarBooks() {
        return calendarBooks;
    }
}
