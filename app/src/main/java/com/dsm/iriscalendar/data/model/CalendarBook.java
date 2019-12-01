package com.dsm.iriscalendar.data.model;

import com.google.gson.annotations.SerializedName;

public class CalendarBook {

    @SerializedName("calendar")
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
