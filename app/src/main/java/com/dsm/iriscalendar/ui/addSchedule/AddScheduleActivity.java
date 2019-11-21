package com.dsm.iriscalendar.ui.addSchedule;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivityMVP;
import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.ui.custom.CategoryView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddScheduleActivity extends BaseActivityMVP implements AddScheduleContract.View {

    @BindView(R.id.et_todo) EditText etTodo;

    @BindView(R.id.et_year) EditText etYear;

    @BindView(R.id.et_month) EditText etMonth;

    @BindView(R.id.et_date) EditText etDate;

    @BindView(R.id.et_required_time) EditText etRequiredTime;

    @BindView(R.id.tv_important) TextView tvImportant;

    @BindView(R.id.cv_add_schedule) CategoryView cvAddSchedule;

    private boolean isImportant = false;

    @Inject
    AddScheduleContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);
        presenter.createView(this);
        presenter.getCategory();

        findViewById(R.id.tv_cancel).setOnClickListener(v -> finish());

        tvImportant.setOnClickListener(v -> {
            if (isImportant) {
                tvImportant.setBackgroundResource(R.drawable.bg_not_important);
                tvImportant.setTextColor(getResources().getColor(R.color.colorPrimary));
                isImportant = false;
            } else {
                tvImportant.setBackgroundResource(R.drawable.bg_important);
                tvImportant.setTextColor(getResources().getColor(R.color.colorWhite));
                isImportant = true;
            }
        });

        findViewById(R.id.tv_complete).setOnClickListener(v -> presenter.addSchedule());
    }


    @Override
    public String getCategory() {
        return cvAddSchedule.getCategory();
    }

    @Override
    public String getTodo() {
        return etTodo.getText().toString().trim();
    }

    @Override
    public String getEndTime() {
        String year = etYear.getText().toString().trim();
        String month = etMonth.getText().toString().trim();
        String date = etDate.getText().toString().trim();

        String time = year + "-" + month + "-" + date;

        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Objects.requireNonNull(new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    @Override
    public int getRequiredTime() {
        try {
            return Integer.parseInt(etRequiredTime.getText().toString().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public boolean getIsParticularImportant() {
        return isImportant;
    }

    @Override
    public void toastBlankError() {
        Toast.makeText(this, R.string.error_blank, Toast.LENGTH_SHORT).show();
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
    public void toastImpossibleSchedule() {
        Toast.makeText(this, R.string.impossible_schedule, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void setCategory(Category category) {
        cvAddSchedule.setCategory(category);
    }
}
