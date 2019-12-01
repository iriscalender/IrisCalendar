package com.dsm.iriscalendar.viewModel;

import com.dsm.iriscalendar.BaseTest;
import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.data.model.CalendarBook;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.data.repository.main.MainRepository;
import com.dsm.iriscalendar.ui.main.MainViewModel;
import com.jraska.livedata.TestObserver;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

import static org.mockito.Mockito.when;

public class MainViewModelTests extends BaseTest {

    @Mock private MainRepository repository;

    private MainViewModel viewModel;

    @Before
    public void init() {
        viewModel = new MainViewModel(repository);
    }

    @Test
    public void getCalendarBookSuccessTest() {
        List<CalendarBook> response = new ArrayList<>();
        response.add(new CalendarBook("purple", "2019-03-04"));
        response.add(new CalendarBook("orange", "2019-03-07"));
        response.add(new CalendarBook("pink", "2019-03-06"));
        response.add(new CalendarBook("blue", "2019-03-05"));

        when(repository.getCalendarBook()).thenReturn(Flowable.just(response));

        viewModel.getCalendarBook();

        TestObserver.test(viewModel.calendarBook).assertValue(response);
    }

    @Test
    public void getCalendarBookFailedTest() {
        when(repository.getCalendarBook()).thenReturn(Flowable.error(createHttpException()));

        viewModel.getCalendarBook();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_server_error);
    }

    @Test
    public void getCalendarScheduleSuccessTest() {
        List<CalendarSchedule> response = new ArrayList<>();
        response.add(new CalendarSchedule(0, "purple", "hello", "10:43", "11:33", false));
        viewModel.selectedDate.setValue("2019-03-01");
        when(repository.getCalendarSchedule(viewModel.selectedDate.getValue())).thenReturn(Flowable.just(response));

        viewModel.getCalendarSchedule();

        TestObserver.test(viewModel.calendarSchedule).assertValue(response);
    }
}
