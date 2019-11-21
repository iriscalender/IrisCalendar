package com.dsm.iriscalendar.ui.signUp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseViewModel;
import com.dsm.iriscalendar.data.repository.signUp.SignUpRepository;
import com.dsm.iriscalendar.util.SingleLiveEvent;

public class SignUpViewModel extends BaseViewModel {

    public MutableLiveData<String> id = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> reType = new MutableLiveData<>();

    public MediatorLiveData<Boolean> isSignUpEnable = new MediatorLiveData<>();

    private SingleLiveEvent<Integer> toastEvent = new SingleLiveEvent<>();
    public LiveData<Integer> getToastEvent() {
        return toastEvent;
    }

    private SingleLiveEvent<Void> intentMainEvent = new SingleLiveEvent<>();
    public LiveData<Void> getIntentMainEvent() {
        return intentMainEvent;
    }

    private SignUpRepository signUpRepository;

    public SignUpViewModel(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;

        id.setValue("");
        password.setValue("");
        reType.setValue("");

        isSignUpEnable.addSource(id, s -> isSignUpEnable());
        isSignUpEnable.addSource(password, s -> isSignUpEnable());
        isSignUpEnable.addSource(reType, s -> isSignUpEnable());
    }

    private void isSignUpEnable() {
        assert id.getValue() != null && password.getValue() != null && reType.getValue() != null;
        if (!id.getValue().trim().isEmpty()
                && !password.getValue().trim().isEmpty()
                && !reType.getValue().trim().isEmpty()) isSignUpEnable.setValue(true);
        else isSignUpEnable.setValue(false);
    }

    public void signUp() {
        assert id.getValue() != null && password.getValue() != null && reType.getValue() != null;
        String id = this.id.getValue().trim();
        String password = this.password.getValue().trim();
        String reType = this.reType.getValue().trim();

        if (id.length() < 6) {
            toastEvent.setValue(R.string.error_short_id);
            return;
        } else if (password.length() < 8) {
            toastEvent.setValue(R.string.error_short_password);
            return;
        } else if (!password.equals(reType)) {
            toastEvent.setValue(R.string.error_not_same_password);
            return;
        }

        addDisposable(
                signUpRepository.signUp(id, password, reType)
                        .subscribe(code -> {
                            switch (code) {
                                case 200:
                                    intentMainEvent.call();
                                    break;
                                case 400:
                                    toastEvent.setValue(R.string.error_invalid_value);
                                    break;
                                case 409:
                                    toastEvent.setValue(R.string.error_user_already_exists);
                                    break;
                                default:
                                    toastEvent.setValue(R.string.error_server_error);
                            }
                        }, throwable -> toastEvent.setValue(R.string.error_server_error))
        );
    }
}
