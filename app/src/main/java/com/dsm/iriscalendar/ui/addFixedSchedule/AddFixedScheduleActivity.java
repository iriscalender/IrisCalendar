package com.dsm.iriscalendar.ui.addFixedSchedule;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.data.model.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFixedScheduleActivity extends BaseActivity implements AddFixedScheduleContract.View {

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

    @BindView(R.id.et_todo)
    EditText etTodo;

    @BindView(R.id.et_year)
    EditText etYear;

    @BindView(R.id.et_month)
    EditText etMonth;

    @BindView(R.id.et_date)
    EditText etDate;

    @BindView(R.id.tv_complete)
    TextView tvComplete;

    @Inject
    AddFixedScheduleContract.Presenter presenter;

    private int upState = R.id.view_first;
    private boolean isStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fixed_schedule);
        ButterKnife.bind(this);
        presenter.createView(this);
        presenter.getCategory();

        animBig.setFillAfter(true);
        viewFirst.startAnimation(animBig);

        npHour.setMinValue(0);
        npHour.setMaxValue(24);

        npMinute.setMinValue(0);
        npMinute.setMaxValue(59);

        findViewById(R.id.tv_cancel).setOnClickListener(v -> finish());

        tvComplete.setOnClickListener(v -> presenter.addFixedSchedule());

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

    @Override
    public String getCategory() {
        if (upState == R.id.view_first)
            return "purple";
        else if (upState == R.id.view_second)
            return "blue";
        else if (upState == R.id.view_third)
            return "pink";
        else
            return "orange";
    }

    @Override
    public String getTodo() {
        return etTodo.getText().toString().trim();
    }

    @Override
    public String getStartTime() {
        String year = etYear.getText().toString().trim();
        String month = etMonth.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String hour = tvStartHour.getText().toString().trim();
        String minute = tvStartMinute.getText().toString().trim();
        String startTime = year + "-" + month + "-" + date + " " + hour + ":" + minute;

        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(Objects.requireNonNull(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).parse(startTime)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startTime;
    }

    @Override
    public String getEndTime() {
        String year = etYear.getText().toString().trim();
        String month = etMonth.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String hour = tvEndHour.getText().toString().trim();
        String minute = tvEndMinute.getText().toString().trim();
        String endTime = year + "-" + month + "-" + date + " " + hour + ":" + minute;

        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(Objects.requireNonNull(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).parse(endTime)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endTime;
    }

    @Override
    public void setCategory(Category category) {
        ((TextView) findViewById(R.id.tv_purple)).setText(category.getPurple());
        ((TextView) findViewById(R.id.tv_blue)).setText(category.getBlue());
        ((TextView) findViewById(R.id.tv_pink)).setText(category.getPink());
        ((TextView) findViewById(R.id.tv_orange)).setText(category.getOrange());
    }

    @Override
    public void toastServerError() {
        Toast.makeText(this, R.string.error_server_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastBlankError() {
        Toast.makeText(this, R.string.error_blank, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastImpossibleSchedule() {
        Toast.makeText(this, R.string.impossible_schedule, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}