package com.dsm.iriscalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.szugyi.circlemenu.view.CircleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeSetActivity extends AppCompatActivity {

    @BindView(R.id.cl_clock)
    CircleLayout clClock;

    @BindView(R.id.tv_complete)
    TextView tvComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);
        ButterKnife.bind(this);

        clClock.setSpeed(25);

        tvComplete.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}
