package com.dsm.iriscalendar.presenter;

import com.dsm.iriscalendar.data.model.TimeResponse;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetContract;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReTimeSetPresenterTests {

    @Mock
    private ReTimeSetContract.View view;

    @Mock
    private ReTimeSetContract.Repository repository;

    private ReTimeSetContract.Presenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new ReTimeSetPresenter(repository);
        presenter.createView(view);
    }

    @Test
    public void getTimeSuccessTest() {
        TimeResponse response = new TimeResponse("START", "END");
        when(repository.getTimeSet())
                .thenReturn(Flowable.just(response));

        presenter.getTimeSet();

        verify(view).setStartTime(response.getStartTime());
        verify(view).setEndTime(response.getEnd());
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

        when(repository.updateTimeSet(view.getStartTime(), view.getEndTime()))
                .thenReturn(Flowable.just(Response.success(200, new Object())));

        presenter.timeSet();

        verify(view).finishActivity();
    }
}
