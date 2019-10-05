package com.dsm.iriscalendar;

import com.dsm.iriscalendar.data.model.AuthResponse;
import com.dsm.iriscalendar.ui.login.LoginContract;
import com.dsm.iriscalendar.ui.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTests {

    @Mock
    private LoginContract.View view;

    @Mock
    private LoginContract.Repository repository;

    private LoginContract.Presenter presenter;

    @Mock
    private ResponseBody failedResponse;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(repository);
        presenter.createView(view);
    }

    @Test
    public void blankErrorTest() {
        when(view.getId()).thenReturn("");
        when(view.getPassword()).thenReturn("");

        presenter.login();

        verify(view).toastBlankError();
    }

    @Test
    public void shortIdTest() {
        when(view.getId()).thenReturn("short");
        when(view.getPassword()).thenReturn("12345678");

        presenter.login();

        verify(view).toastShortId();
    }

    @Test
    public void shortPasswordTest() {
        when(view.getId()).thenReturn("123456");
        when(view.getPassword()).thenReturn("short");

        presenter.login();

        verify(view).toastShortPassword();
    }

    @Test
    public void loginSuccessTest() {
        when(view.getId()).thenReturn("123456");
        when(view.getPassword()).thenReturn("12345678");

        when(repository.login(view.getId(), view.getPassword()))
                .thenReturn(Flowable.just(Response.success(200, new AuthResponse())));

        presenter.login();

        verify(view).finishActivity();
        verify(view).startMainActivity();
    }
}
