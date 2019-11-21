package com.dsm.iriscalendar.viewModel;

import com.dsm.iriscalendar.BaseTest;
import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.data.repository.signUp.SignUpRepository;
import com.dsm.iriscalendar.ui.signUp.SignUpViewModel;
import com.jraska.livedata.TestObserver;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Flowable;

import static org.mockito.Mockito.when;

public class SignUpViewModelTests extends BaseTest {

    @Mock private SignUpRepository signUpRepository;

    private SignUpViewModel viewModel;

    @Before
    public void init() {
        viewModel = new SignUpViewModel(signUpRepository);
    }

    @Test
    public void signUpBtnEnableTest() {
        viewModel.id.setValue("ID");
        viewModel.password.setValue("PASSWORD");
        viewModel.reType.setValue("RE_TYPE");

        TestObserver.test(viewModel.isSignUpEnable).assertValue(true);
    }

    @Test
    public void signUpDisableTest() {
        viewModel.id.setValue("ID");
        viewModel.password.setValue("");
        viewModel.reType.setValue("RE_TYPE");

        TestObserver.test(viewModel.isSignUpEnable).assertValue(false);
    }

    @Test
    public void shortIdTest() {
        viewModel.id.setValue("ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");
        viewModel.reType.setValue("PASSWORD_PASSWORD");

        viewModel.signUp();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_short_id);
    }

    @Test
    public void shortPasswordTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASS");
        viewModel.reType.setValue("PASS");

        viewModel.signUp();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_short_password);
    }

    @Test
    public void diffPasswordTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");
        viewModel.reType.setValue("PASSWORD_PASSWORD123123123");

        viewModel.signUp();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_not_same_password);
    }

    @Test
    public void signUpSuccessTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");
        viewModel.reType.setValue("PASSWORD_PASSWORD");

        assert viewModel.id.getValue() != null && viewModel.password.getValue() != null
                && viewModel.reType.getValue() != null;
        String id = viewModel.id.getValue().trim();
        String password = viewModel.password.getValue().trim();
        String reType = viewModel.reType.getValue().trim();

        when(signUpRepository.signUp(id, password, reType)).thenReturn(Flowable.just(200));

        viewModel.signUp();

        TestObserver.test(viewModel.getIntentMainEvent()).assertHasValue();
    }

    @Test
    public void invalidValueTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");
        viewModel.reType.setValue("PASSWORD_PASSWORD");

        assert viewModel.id.getValue() != null && viewModel.password.getValue() != null
                && viewModel.reType.getValue() != null;
        String id = viewModel.id.getValue().trim();
        String password = viewModel.password.getValue().trim();
        String reType = viewModel.reType.getValue().trim();

        when(signUpRepository.signUp(id, password, reType)).thenReturn(Flowable.just(400));

        viewModel.signUp();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_invalid_value);
    }

    @Test
    public void userAlreadyExistTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");
        viewModel.reType.setValue("PASSWORD_PASSWORD");

        assert viewModel.id.getValue() != null && viewModel.password.getValue() != null
                && viewModel.reType.getValue() != null;
        String id = viewModel.id.getValue().trim();
        String password = viewModel.password.getValue().trim();
        String reType = viewModel.reType.getValue().trim();

        when(signUpRepository.signUp(id, password, reType)).thenReturn(Flowable.just(409));

        viewModel.signUp();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_user_already_exists);
    }

    @Test
    public void serverErrorTest() {
        viewModel.id.setValue("LONG_ENOUGH_ID");
        viewModel.password.setValue("PASSWORD_PASSWORD");
        viewModel.reType.setValue("PASSWORD_PASSWORD");

        assert viewModel.id.getValue() != null && viewModel.password.getValue() != null
                && viewModel.reType.getValue() != null;
        String id = viewModel.id.getValue().trim();
        String password = viewModel.password.getValue().trim();
        String reType = viewModel.reType.getValue().trim();

        when(signUpRepository.signUp(id, password, reType)).thenReturn(Flowable.error(createHttpException()));

        viewModel.signUp();

        TestObserver.test(viewModel.getToastEvent()).assertValue(R.string.error_server_error);
    }
}
