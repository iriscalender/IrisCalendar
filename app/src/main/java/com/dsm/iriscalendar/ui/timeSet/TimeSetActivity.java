package com.dsm.iriscalendar.ui.timeSet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.ui.main.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeSetActivity extends BaseActivity implements TimeSetContract.View {

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

    @BindView(R.id.btn_set_time)
    Button btnSetTime;

    private boolean isStart = true;

    @Inject
    TimeSetContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);
        ButterKnife.bind(this);
        presenter.createView(this);

        npMeridiem.setMinValue(0);
        npMeridiem.setMaxValue(1);
        npMeridiem.setDisplayedValues(new String[]{"AM", "PM"});
        npMeridiem.setWrapSelectorWheel(false);

        npHour.setMinValue(1);
        npHour.setMaxValue(12);

        npMinute.setMinValue(0);
        npMinute.setMaxValue(1);
        npMinute.setDisplayedValues(new String[]{"00", "30"});

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
                tvStartHour.setText(String.valueOf(i1));
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

        btnSetTime.setOnClickListener(v -> presenter.timeSet());
    }

    @Override
    public String getStartTime() {
        String time = tvStartHour.getText().toString() + ":" + tvStartMinute.getText().toString() + " " + tvStartMeridiem.getText().toString();
        SimpleDateFormat simple12Format = new SimpleDateFormat("hh:mm a", Locale.US);
        SimpleDateFormat simple24Format = new SimpleDateFormat("HH:mm", Locale.US);
        try {
            return simple24Format.format(Objects.requireNonNull(simple12Format.parse(time)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getEndTime() {
        String time = tvEndHour.getText().toString() + ":" + tvEndMinute.getText().toString() + " " + tvEndMeridiem.getText().toString();
        SimpleDateFormat simple12Format = new SimpleDateFormat("hh:mm a", Locale.US);
        SimpleDateFormat simple24Format = new SimpleDateFormat("HH:mm", Locale.US);
        try {
            return simple24Format.format(Objects.requireNonNull(simple12Format.parse(time)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void toastStartTimeFast() {
        Toast.makeText(this, R.string.error_start_time_faster, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastSameTime() {
        Toast.makeText(this, R.string.error_same_time, Toast.LENGTH_SHORT).show();
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
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
