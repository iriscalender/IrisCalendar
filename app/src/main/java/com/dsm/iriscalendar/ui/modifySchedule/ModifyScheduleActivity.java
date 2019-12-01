package com.dsm.iriscalendar.ui.modifySchedule;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivityMVP;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.ui.custom.CategoryView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModifyScheduleActivity extends BaseActivityMVP implements ModifyScheduleContract.View {

    @BindView(R.id.et_todo) EditText etTodo;

    @BindView(R.id.et_year) EditText etYear;

    @BindView(R.id.et_month) EditText etMonth;

    @BindView(R.id.et_date) EditText etDate;

    @BindView(R.id.et_required_time) EditText etRequiredTime;

    @BindView(R.id.cv_modify_schedule) CategoryView cvModifySchedule;

    private String startTime = "";
    private String endTime = "";

    @Inject ModifyScheduleContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_schedule);
        ButterKnife.bind(this);
        presenter.createView(this);

        findViewById(R.id.tv_cancel).setOnClickListener(v -> finish());

        int id = getIntent().getIntExtra("id", -1);

        presenter.getCategory();
        presenter.getSchedule(id);

        findViewById(R.id.tv_complete).setOnClickListener(v -> {
            String calendarName = etTodo.getText().toString().trim();
            String category = cvModifySchedule.getCategory();
            SimpleDateFormat format =  new SimpleDateFormat("HH:mm", Locale.KOREA);
            String end = "";
            try {
                end = endTime.split(" ")[0] + " " + format.format(Objects.requireNonNull(format.parse(etRequiredTime.getText().toString().trim())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            presenter.modifySchedule(id, category, calendarName, startTime, end);
        });
    }

    @Override
    public void setCategory(Category category) {
        cvModifySchedule.setCategory(category);
    }

    @Override
    public void setSchedule(CalendarSchedule schedule) {
        cvModifySchedule.selectCategory(schedule.getCategory());

        String endDate = schedule.getEndTime().split(" ")[0];
        etYear.setText(endDate.split("-")[0]);
        etMonth.setText(endDate.split("-")[1]);
        etDate.setText(endDate.split("-")[2]);

        startTime = schedule.getStartTime();
        endTime = schedule.getEndTime();

        etTodo.setText(schedule.getCalendarName());

        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.KOREA);
        try {
            Date startTime = format.parse(schedule.getStartTime().split(" ")[1]);
            Date endTime = format.parse(schedule.getEndTime().split(" ")[1]);
            assert endTime != null;
            assert startTime != null;
            long diff = endTime.getTime() - startTime.getTime();
            long diffHours = diff / (60 * 60 * 1000) % 24;
            etRequiredTime.setText(String.valueOf(diffHours));
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }
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
    public void finishActivity() {
        finish();
    }
}
