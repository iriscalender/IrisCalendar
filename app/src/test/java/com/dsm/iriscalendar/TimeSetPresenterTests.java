package com.dsm.iriscalendar;

import com.dsm.iriscalendar.ui.timeSet.TimeSetContract;
import com.dsm.iriscalendar.ui.timeSet.TimeSetPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TimeSetPresenterTests {

    @Mock
    private TimeSetContract.View view;

    @Mock
    private TimeSetContract.Repository repository;

    private TimeSetContract.Presenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new TimeSetPresenter(repository);
        presenter.createView(view);
    }

    @Test
    public void sameTimeTest() {
        when(view.getStartTime()).thenReturn("12:30");
        when(view.getEndTime()).thenReturn("12:30");

        presenter.timeSet();

        verify(view).toastSameTime();
    }

    @Test
    public void whenStartTimeIsFasterTest1() {
        when(view.getStartTime()).thenReturn("12:30");
        when(view.getEndTime()).thenReturn("12:29");

        presenter.timeSet();

        verify(view).toastStartTimeFast();
    }

    @Test
    public void whenStartTimeIsFasterTest2() {
        when(view.getStartTime()).thenReturn("13:30");
        when(view.getEndTime()).thenReturn("12:29");

        presenter.timeSet();

        verify(view).toastStartTimeFast();
    }

    @Test
    public void timeSetSuccessTest() {
        when(view.getStartTime()).thenReturn("11:30");
        when(view.getEndTime()).thenReturn("12:29");

        when(repository.timeSet(view.getStartTime(), view.getEndTime()))
                .thenReturn(Flowable.just(Response.success(200, new Object())));

        presenter.timeSet();

        verify(view).startMainActivity();
    }
}
