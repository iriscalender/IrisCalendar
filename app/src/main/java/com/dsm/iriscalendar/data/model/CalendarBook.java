package com.dsm.iriscalendar.data.model;

public class CalendarBook {

    private String category;
    private String date;

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public CalendarBook(String category, String date) {
        this.category = category;
        this.date = date;
    }
}
