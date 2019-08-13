package com.dsm.iriscalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.szugyi.circlemenu.view.CircleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.hellocsl.cursorwheel.CursorWheelLayout;

public class TimeSetActivity extends AppCompatActivity {

    @BindView(R.id.tv_complete)
    TextView tvComplete;

    @BindView(R.id.cwl_time)
    CursorWheelLayout cwlTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);
        ButterKnife.bind(this);

        tvComplete.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

        cwlTime.setAdapter(new WheelClockAdapter(this));

    }
}
