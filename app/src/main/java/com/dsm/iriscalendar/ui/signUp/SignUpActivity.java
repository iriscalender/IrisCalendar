package com.dsm.iriscalendar.ui.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    @BindView(R.id.et_id)
    EditText etId;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_password_confirm)
    EditText etReType;

    @BindView(R.id.btn_sign_up)
    Button btnSignUp;

    @Inject
    SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        presenter.createView(this);

        btnSignUp.setOnClickListener(v -> presenter.signUp());
    }

    @Override
    public String getInputId() {
        return etId.getText().toString().trim();
    }

    @Override
    public String getInputPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public String getInputReType() {
        return etReType.getText().toString().trim();
    }

    @Override
    public void toastBlankError() {
        Toast.makeText(this, R.string.error_blank, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastShortId() {
        Toast.makeText(this, R.string.error_short_id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastShortPassword() {
        Toast.makeText(this, R.string.error_short_password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastNotSamePassword() {
        Toast.makeText(this, R.string.error_not_same_password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastServerError() {
        Toast.makeText(this, R.string.error_server_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastInvalidValue() {
        Toast.makeText(this, R.string.error_invalid_value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastUserAlreadyExists() {
        Toast.makeText(this, R.string.error_user_already_exists, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
