package com.dsm.iriscalendar.presenter;

import com.dsm.iriscalendar.data.model.AuthResponse;
import com.dsm.iriscalendar.ui.signUp.SignUpContract;
import com.dsm.iriscalendar.ui.signUp.SignUpPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SignUpPresenterTests {

    @Mock
    private SignUpContract.View view;

    @Mock
    private SignUpContract.Repository repository;

    private SignUpContract.Presenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new SignUpPresenter(repository);
        presenter.createView(view);
    }

    @Test
    public void blankErrorTest() {
        when(view.getInputId()).thenReturn("ID");
        when(view.getInputPassword()).thenReturn("");
        when(view.getInputReType()).thenReturn("");

        presenter.signUp();

        verify(view).toastBlankError();
    }

    @Test
    public void shortIdTest() {
        when(view.getInputId()).thenReturn("short");
        when(view.getInputPassword()).thenReturn("12345678");
        when(view.getInputReType()).thenReturn("12345678");

        presenter.signUp();

        verify(view).toastShortId();
    }

    @Test
    public void shortPasswordTest() {
        when(view.getInputId()).thenReturn("123456");
        when(view.getInputPassword()).thenReturn("short");
        when(view.getInputReType()).thenReturn("short");

        presenter.signUp();

        verify(view).toastShortPassword();
    }

    @Test
    public void notSamePasswordTest() {
        when(view.getInputId()).thenReturn("123456");
        when(view.getInputPassword()).thenReturn("PASSWORD_PASSWORD");
        when(view.getInputReType()).thenReturn("PASSWORD_PASSWORD_PASSWORD");

        presenter.signUp();

        verify(view).toastNotSamePassword();
    }

    @Test
    public void signUpSuccessTest() {
        when(view.getInputId()).thenReturn("123456");
        when(view.getInputPassword()).thenReturn("12345678");
        when(view.getInputReType()).thenReturn("12345678");

        when(repository.signUp(view.getInputId(), view.getInputPassword(), view.getInputReType()))
                .thenReturn(Flowable.just(Response.success(200, new AuthResponse())));

        presenter.signUp();

        verify(view).startMainActivity();
    }
}
