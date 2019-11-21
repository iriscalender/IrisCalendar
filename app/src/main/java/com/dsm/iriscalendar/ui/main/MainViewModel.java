package com.dsm.iriscalendar.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseViewModel;
import com.dsm.iriscalendar.data.model.CalendarBook;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.data.repository.main.MainRepository;
import com.dsm.iriscalendar.util.SingleLiveEvent;

import java.util.List;

public class MainViewModel extends BaseViewModel {

    public MutableLiveData<String> selectedDate = new MutableLiveData<>();
    public MutableLiveData<List<CalendarBook>> calendarBook = new MutableLiveData<>();
    public MutableLiveData<List<CalendarSchedule>> calendarSchedule = new MutableLiveData<>();

    private SingleLiveEvent<Integer> toastEvent = new SingleLiveEvent<>();
    public LiveData<Integer> getToastEvent() {
        return toastEvent;
    }

    private MainRepository repository;

    public MainViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void getCalendarBook() {
        addDisposable(
                repository.getCalendarBook()
                        .subscribe(
                                response -> calendarBook.setValue(response),
                                throwable -> toastEvent.setValue(R.string.error_server_error)
                        )
        );
    }

    public void getCalendarSchedule() {
        addDisposable(
                repository.getCalendarSchedule(selectedDate.getValue())
                    .subscribe(
                            response -> calendarSchedule.setValue(response),
                            throwable -> toastEvent.setValue(R.string.error_server_error)
                    )
        );
    }

}
