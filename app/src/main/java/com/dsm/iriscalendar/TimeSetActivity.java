package com.dsm.iriscalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.szugyi.circlemenu.view.CircleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.hellocsl.cursorwheel.CursorWheelLayout;

public class TimeSetActivity extends AppCompatActivity {

    @BindView(R.id.tv_complete)
    TextView tvComplete;

    @BindView(R.id.tv_start_meridiem)
    TextView tvStartMeridiem;

    @BindView(R.id.tv_start_hour)
    TextView tvStartHour;

    @BindView(R.id.tv_start_minute)
    TextView tvStartMinute;

    @BindView(R.id.tv_end_meridiem)
    TextView tvEndMeridiem;

    @BindView(R.id.tv_end_hour)
    TextView tvEndHour;

    @BindView(R.id.tv_end_minute)
    TextView tvEndMinute;

    @BindView(R.id.np_meridiem)
    NumberPicker npMeridiem;

    @BindView(R.id.np_hour)
    NumberPicker npHour;

    @BindView(R.id.np_minute)
    NumberPicker npMinute;

    @BindView(R.id.tv_set_start)
    TextView tvSetStart;

    @BindView(R.id.tv_set_end)
    TextView tvSetEnd;

    private boolean isStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);
        ButterKnife.bind(this);

        tvComplete.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

        npMeridiem.setMinValue(0);
        npMeridiem.setMaxValue(1);
        npMeridiem.setDisplayedValues(new String[] { "AM", "PM" });
        npMeridiem.setWrapSelectorWheel(false);

        npHour.setMinValue(1);
        npHour.setMaxValue(12);

        npMinute.setMinValue(0);
        npMinute.setMaxValue(1);
        npMinute.setDisplayedValues(new String[] { "00", "30" });

        tvSetStart.setOnClickListener(v -> {
            isStart = true;

            tvSetStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            tvSetEnd.setTextColor(getResources().getColor(R.color.colorGrey));

            if (tvStartMeridiem.getText().toString().equals("AM")) {
                npMeridiem.setValue(0);
            } else {
                npMeridiem.setValue(1);
            }

            npHour.setValue(Integer.parseInt(tvStartHour.getText().toString()));

            if (tvStartMinute.getText().toString().equals("00")) {
                npMinute.setValue(0);
            } else {
                npMinute.setValue(1);
            }
        });

        tvSetEnd.setOnClickListener(v -> {
            isStart = false;

            tvSetStart.setTextColor(getResources().getColor(R.color.colorGrey));
            tvSetEnd.setTextColor(getResources().getColor(R.color.colorPrimary));

            if (tvEndMeridiem.getText().toString().equals("AM")) {
                npMeridiem.setValue(0);
            } else {
                npMeridiem.setValue(1);
            }

            npHour.setValue(Integer.parseInt(tvEndHour.getText().toString()));

            if (tvEndMinute.getText().toString().equals("00")) {
                npMinute.setValue(0);
            } else {
                npMinute.setValue(1);
            }
        });

        npMeridiem.setOnValueChangedListener((numberPicker, i, i1) -> {
            if (isStart) {
                if (i1 == 0) {
                    tvStartMeridiem.setText(getString(R.string.am));
                } else {
                    tvStartMeridiem.setText(getString(R.string.pm));
                }
            } else {
                if (i1 == 0) {
                    tvEndMeridiem.setText(getString(R.string.am));
                } else {
                    tvEndMeridiem.setText(getString(R.string.pm));
                }
            }
        });

        npHour.setOnValueChangedListener((numberPicker, i, i1) -> {
            if (isStart) {
                tvStartHour.setText(String.valueOf(i));
            } else {
                tvEndHour.setText(String.valueOf(i1));
            }
        });

        npMinute.setOnValueChangedListener((numberPicker, i, i1) -> {
            if (isStart) {
                if (i1 == 0) {
                    tvStartMinute.setText(getString(R.string._00));
                } else {
                    tvStartMinute.setText(getString(R.string._30));
                }
            } else {
                if (i1 == 0) {
                    tvEndMinute.setText(getString(R.string._00));
                } else {
                    tvEndMinute.setText(getString(R.string._30));
                }
            }
        });
    }
}
