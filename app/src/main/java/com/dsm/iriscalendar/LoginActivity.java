package com.dsm.iriscalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tv_sign_up)
    TextView tvSignUP;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        tvSignUP.setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));

        btnLogin.setOnClickListener(v -> startActivity(new Intent(this, TimeSetActivity.class)));
    }
}
