package com.dsm.iriscalendar;

import com.dsm.iriscalendar.ui.activity.MainContract;
import com.dsm.iriscalendar.ui.activity.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainPresenterTests {

    @Mock
    private MainContract.View view;

    @Mock
    private MainContract.Repository repository;

    private MainContract.Presenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(repository);
        presenter.createView(view);
    }

    @Test
    public void getCalendarSuccessTest() {
    }
}
