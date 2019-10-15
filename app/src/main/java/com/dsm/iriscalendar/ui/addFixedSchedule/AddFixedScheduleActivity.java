package com.dsm.iriscalendar.ui.addFixedSchedule;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dsm.iriscalendar.R;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFixedScheduleActivity extends AppCompatActivity {

    @BindAnim(R.anim.anim_big)
    Animation animBig;

    @BindAnim(R.anim.anim_small)
    Animation animSmall;

    @BindView(R.id.view_first)
    View viewFirst;

    @BindView(R.id.view_second)
    View viewSecond;

    @BindView(R.id.view_third)
    View viewThird;

    @BindView(R.id.view_fourth)
    View viewFourth;

    @BindView(R.id.np_hour)
    NumberPicker npHour;

    @BindView(R.id.np_minute)
    NumberPicker npMinute;

    @BindView(R.id.tv_set_start)
    TextView tvSetStart;

    @BindView(R.id.tv_set_end)
    TextView tvSetEnd;

    @BindView(R.id.tv_start_hour)
    TextView tvStartHour;

    @BindView(R.id.tv_start_minute)
    TextView tvStartMinute;

    @BindView(R.id.tv_end_hour)
    TextView tvEndHour;

    @BindView(R.id.tv_end_minute)
    TextView tvEndMinute;

    private int upState = R.id.view_first;
    private boolean isStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fixed_schedule);
        ButterKnife.bind(this);

        animBig.setFillAfter(true);
        viewFirst.startAnimation(animBig);

        npHour.setMinValue(0);
        npHour.setMaxValue(24);

        npMinute.setMinValue(0);
        npMinute.setMaxValue(59);

        findViewById(R.id.tv_cancel).setOnClickListener(v -> finish());

        tvSetStart.setOnClickListener(v -> {
            isStart = !isStart;

            tvSetStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            tvSetEnd.setTextColor(getResources().getColor(R.color.colorGrey));

            npHour.setValue(Integer.parseInt(tvStartHour.getText().toString()));
            npMinute.setValue(Integer.parseInt(tvStartMinute.getText().toString()));
        });

        tvSetEnd.setOnClickListener(v -> {
            isStart = !isStart;

            tvSetEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            tvSetStart.setTextColor(getResources().getColor(R.color.colorGrey));

            npHour.setValue(Integer.parseInt(tvEndHour.getText().toString()));
            npMinute.setValue(Integer.parseInt(tvEndMinute.getText().toString()));
        });

        npHour.setOnValueChangedListener((picker, oldVal, newVal) -> {
            if (isStart) {
                tvStartHour.setText(String.valueOf(newVal));
            } else {
                tvEndHour.setText(String.valueOf(newVal));
            }
        });

        npMinute.setOnValueChangedListener((picker, oldVal, newVal) -> {
            if (isStart) {
                tvStartMinute.setText(String.valueOf(newVal));
            } else {
                tvEndMinute.setText(String.valueOf(newVal));
            }
        });
    }

    public void onClickCategory(View v) {
        int clickedView = v.getId();

        if (clickedView == upState) {
            return;
        }

        switch (upState) {
            case R.id.view_first:
                viewFirst.startAnimation(animSmall);
                break;
            case R.id.view_second:
                viewSecond.startAnimation(animSmall);
                break;
            case R.id.view_third:
                viewThird.startAnimation(animSmall);
                break;
            case R.id.view_fourth:
                viewFourth.startAnimation(animSmall);
                break;
        }

        if (clickedView == R.id.view_first) {
            viewFirst.startAnimation(animBig);
            upState = R.id.view_first;
        } else if (clickedView == R.id.view_second) {
            viewSecond.startAnimation(animBig);
            upState = R.id.view_second;
        } else if (clickedView == R.id.view_third) {
            viewThird.startAnimation(animBig);
            upState = R.id.view_third;
        } else if (clickedView == R.id.view_fourth) {
            viewFourth.startAnimation(animBig);
            upState = R.id.view_fourth;
        }
    }
}
