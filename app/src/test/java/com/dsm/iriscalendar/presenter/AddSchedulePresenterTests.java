package com.dsm.iriscalendar.presenter;

import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.data.model.Schedule;
import com.dsm.iriscalendar.ui.addSchedule.AddScheduleContract;
import com.dsm.iriscalendar.ui.addSchedule.AddSchedulePresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddSchedulePresenterTests {

    @Mock
    private AddScheduleContract.Repository repository;

    @Mock
    private AddScheduleContract.View view;

    private AddScheduleContract.Presenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new AddSchedulePresenter(repository);
        presenter.createView(view);
    }

    @Test
    public void blankErrorTest() {
        when(view.getCategory()).thenReturn("");
        when(view.getTodo()).thenReturn("");
        when(view.getEndTime()).thenReturn("");
        when(view.getRequiredTime()).thenReturn(0);
        when(view.getIsParticularImportant()).thenReturn(true);

        presenter.addSchedule();

        verify(view).toastBlankError();
    }

    @Test
    public void getCategory() {
        Category response = new Category();
        when(repository.getCategory())
                .thenReturn(Flowable.just(response));

        presenter.getCategory();

        verify(view).setCategory(response);
    }

    @Test
    public void addScheduleSuccessTest() {
        when(view.getCategory()).thenReturn("CATEGORY");
        when(view.getTodo()).thenReturn("TODO");
        when(view.getEndTime()).thenReturn("2019-04-05");
        when(view.getRequiredTime()).thenReturn(6);
        when(view.getIsParticularImportant()).thenReturn(true);

        when(repository.addSchedule(
                view.getCategory(),
                view.getTodo(),
                view.getEndTime(),
                view.getRequiredTime(),
                view.getIsParticularImportant()
        )).thenReturn(Flowable.just(Response.success(200, new Schedule())));

        presenter.addSchedule();

        verify(view).finishActivity();
    }
}
