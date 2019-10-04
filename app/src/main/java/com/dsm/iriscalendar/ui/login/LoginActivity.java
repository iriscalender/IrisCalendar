package com.dsm.iriscalendar.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.ui.activity.MainActivity;
import com.dsm.iriscalendar.ui.activity.SignUpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.tv_sign_up)
    TextView tvSignUP;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.et_id)
    EditText etId;

    @BindView(R.id.et_password)
    EditText etPassword;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.createView(this);

        tvSignUP.setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));

        btnLogin.setOnClickListener(v -> presenter.login());

        etPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.login();
                return true;
            }
            return false;
        });
    }

    @Override
    public String getId() {
        return etId.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void toastBlankError() {
        Toast.makeText(this, R.string.error_blank, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void toastShortId() {
        Toast.makeText(this, R.string.error_short_id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastServerError() {
        Toast.makeText(this, R.string.error_server_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastInvalidValue() {
        Toast.makeText(this, R.string.error_login_invalid_value, Toast.LENGTH_SHORT).show();
    }
}
