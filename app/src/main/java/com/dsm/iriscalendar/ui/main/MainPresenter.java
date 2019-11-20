package com.dsm.iriscalendar.ui.main;

import com.dsm.iriscalendar.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.Repository repository;

    public MainPresenter(MainContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void getCalendarBook() {
        addDisposable(
                repository.getCalendarBook()
                        .subscribe(
                                response -> view.setCalendarBook(response),
                                throwable -> view.toastServerError()
                        )
        );
    }

    @Override
    public void getCalendarSchedule(String date) {
        addDisposable(
                repository.getCalendarSchedule(date)
                        .subscribe(
                                response -> view.setCalendarSchedule(response),
                                throwable -> view.toastServerError()
                        )
        );
    }
}
