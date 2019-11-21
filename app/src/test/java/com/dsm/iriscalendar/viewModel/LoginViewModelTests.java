package com.dsm.iriscalendar.viewModel;

import com.dsm.iriscalendar.BaseTest;
import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.data.repository.login.LoginRepository;
import com.dsm.iriscalendar.ui.login.LoginViewModel;
import com.jraska.livedata.TestObserver;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Flowable;

import static org.mockito.Mockito.when;

public class LoginViewModelTests extends BaseTest {

    @Mock private LoginRepository loginRepository;

    private LoginViewModel viewModel;

    @Before
    public void init() {
        viewModel = new LoginViewModel(loginRepository);
    }

    @Test
    public void loginBtnEnableTest() {
        viewModel.id.setValue("ID");
        viewModel.password.setValue("PASSWORD");

        TestObserver.test(viewModel.isLoginEnable).assertValue(true);
    }

    @Test
    public void loginBtnDisableTest() {
        viewModel.id.setValue("");
        viewModel.password.setValue("PASSWORD");

        TestObserver.test(viewModel.isLoginEnable).assertValue(false);
    }

    @Test
    public void shortIdTest() {
        viewModel.id.setValue("id");
        viewModel.password.setValue("PASSWORDPASSWORD");

        viewModel.login();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_short_id);
    }

    @Test
    public void shortPasswordTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PAS");

        viewModel.login();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_short_password);
    }

    @Test
    public void loginSuccessTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");

        assert viewModel.id.getValue() != null && viewModel.password.getValue() != null;
        String id = viewModel.id.getValue().trim();
        String password = viewModel.password.getValue().trim();

        when(loginRepository.login(id, password)).thenReturn(Flowable.just(200));

        viewModel.login();

        TestObserver.test(viewModel.getFinishActivityEvent()).assertHasValue();
        TestObserver.test(viewModel.getIntentMainEvent()).assertHasValue();
    }

    @Test
    public void invalidValueTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");

        assert viewModel.id.getValue() != null && viewModel.password.getValue() != null;
        String id = viewModel.id.getValue().trim();
        String password = viewModel.password.getValue().trim();

        when(loginRepository.login(id, password)).thenReturn(Flowable.just(400));

        viewModel.login();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_invalid_value);
    }

    @Test
    public void loginFailedTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");

        assert viewModel.id.getValue() != null && viewModel.password.getValue() != null;
        String id = viewModel.id.getValue().trim();
        String password = viewModel.password.getValue().trim();

        when(loginRepository.login(id, password)).thenReturn(Flowable.error(createHttpException()));

        viewModel.login();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_server_error);
    }
}
