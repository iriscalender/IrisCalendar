package com.dsm.iriscalendar.presenter;

import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.data.model.FixedSchedule;
import com.dsm.iriscalendar.ui.addFixedSchedule.AddFixedScheduleContract;
import com.dsm.iriscalendar.ui.addFixedSchedule.AddFixedSchedulePresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddFixedSchedulePresenterTests {

    @Mock
    private AddFixedScheduleContract.View view;

    @Mock
    private AddFixedScheduleContract.Repository repository;

    private AddFixedScheduleContract.Presenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new AddFixedSchedulePresenter(repository);
        presenter.createView(view);
    }

    @Test
    public void getCategoryListSuccessTest() {
        Category response = new Category();
        when(repository.getCategory())
                .thenReturn(Flowable.just(response));

        presenter.getCategory();

        verify(view).setCategory(response);
    }

    @Test
    public void blankErrorTest() {
        when(view.getCategory()).thenReturn("");
        when(view.getTodo()).thenReturn("");
        when(view.getStartTime()).thenReturn("");
        when(view.getEndTime()).thenReturn("");

        presenter.addFixedSchedule();

        verify(view).toastBlankError();
    }

    @Test
    public void addFixedScheduleSuccess() {
        when(view.getCategory()).thenReturn("CATEGORY");
        when(view.getTodo()).thenReturn("TODO");
        when(view.getStartTime()).thenReturn("2019-01-03 13:00");
        when(view.getEndTime()).thenReturn("2019-01-03 14:00");

        when(repository.addFixedSchedule(
                view.getCategory(),
                view.getTodo(),
                view.getStartTime(),
                view.getEndTime())
        ).thenReturn(Flowable.just(Response.success(200, new FixedSchedule())));

        presenter.addFixedSchedule();

        verify(view).finishActivity();
    }
}
