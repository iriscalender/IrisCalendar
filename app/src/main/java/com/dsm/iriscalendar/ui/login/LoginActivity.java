package com.dsm.iriscalendar.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.databinding.ActivityLoginBinding;
import com.dsm.iriscalendar.ui.main.MainActivity;
import com.dsm.iriscalendar.ui.signUp.SignUpActivity;
import com.dsm.iriscalendar.util.LoadingDialog;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Inject LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setViewModel(viewModel);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    public void viewInit() {
        findViewById(R.id.tv_sign_up).setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));
    }

    @Override
    public void observeViewModel() {
        viewModel.getToastEvent().observe(this, stringId -> Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show());

        viewModel.getIntentMainEvent().observe(this, t -> startActivity(new Intent(this, MainActivity.class)));

        viewModel.getFinishActivityEvent().observe(this, t -> finish());

        viewModel.getShowLoadingEvent().observe(this, t -> LoadingDialog.show(getSupportFragmentManager()));

        viewModel.getHideLoadingEvent().observe(this, t -> LoadingDialog.hide());
    }
}
