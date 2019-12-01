package com.dsm.iriscalendar.util;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.dsm.iriscalendar.data.model.CalendarBook;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BindingUtil {

    @BindingAdapter("bind:calendarBook")
    public static void calendarBook(CalendarView view, MutableLiveData<List<CalendarBook>> books) {
        if (books.getValue() == null) return;
        Map<String, Calendar> result = new HashMap<>();

        for (CalendarBook calendar : books.getValue()) {
            String calendarDate = calendar.getDate();
            String year = calendarDate.split("-")[0];
            String month = calendarDate.split("-")[1];
            String date = calendarDate.split("-")[2];

            int color = 0;
            switch (calendar.getCategory()) {
                case "purple":
                    color = 0xFF7247B2;
                    break;
                case "blue":
                    color = 0xFF3CB8EF;
                    break;
                case "pink":
                    color = 0xFFD92D73;
                    break;
                case "orange":
                    color = 0xFFFAA86B;
                    break;
            }
            Calendar mapValue = getSchemeCalendar(Integer.valueOf(year.trim()), Integer.valueOf(month.trim()), Integer.valueOf(date.trim()), color);
            result.put(mapValue.toString(), mapValue);
        }

        view.setSchemeDate(result);
    }

    private static Calendar getSchemeCalendar(int year, int month, int day, int color) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);
        return calendar;
    }
}
