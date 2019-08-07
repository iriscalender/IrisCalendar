package com.dsm.iriscalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModifyCategoryActivity extends AppCompatActivity {

    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_category);
        ButterKnife.bind(this);

        tvCancel.setOnClickListener(v -> finish());
    }
}
