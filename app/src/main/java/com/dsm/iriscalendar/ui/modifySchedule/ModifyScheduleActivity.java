package com.dsm.iriscalendar.ui.modifySchedule;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.data.model.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ModifyScheduleActivity extends BaseActivity implements ModifyScheduleContract.View {

    @BindView(R.id.view_first)
    View viewFirst;

    @BindView(R.id.view_second)
    View viewSecond;

    @BindView(R.id.view_third)
    View viewThird;

    @BindView(R.id.view_fourth)
    View viewFourth;

    @BindAnim(R.anim.anim_big)
    Animation animBig;

    @BindAnim(R.anim.anim_small)
    Animation animSmall;

    @BindView(R.id.et_todo)
    EditText etTodo;

    @BindView(R.id.et_year)
    EditText etYear;

    @BindView(R.id.et_month)
    EditText etMonth;

    @BindView(R.id.et_date)
    EditText etDate;

    @BindView(R.id.et_required_time)
    EditText etRequiredTime;

    private int upState;

    private String startTime = "";
    private String endTime = "";

    @Inject
    ModifyScheduleContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_schedule);
        ButterKnife.bind(this);
        presenter.createView(this);
        animBig.setFillAfter(true);

        findViewById(R.id.tv_cancel).setOnClickListener(v -> finish());

        int id = getIntent().getIntExtra("id", -1);

        presenter.getCategory();
        presenter.getSchedule(id);

        findViewById(R.id.tv_complete).setOnClickListener(v -> {
            String calendarName = etTodo.getText().toString().trim();
            String category = "";
            switch (upState) {
                case R.id.view_first:
                    category = "purple";
                    break;
                case R.id.view_second:
                    category = "blue";
                    break;
                case R.id.view_third:
                    category = "pink";
                    break;
                case R.id.view_fourth:
                    category = "orange";
                    break;
            }
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
    public void setCategory(Category category) {
        ((TextView) findViewById(R.id.tv_blue)).setText(category.getBlue());
        ((TextView) findViewById(R.id.tv_purple)).setText(category.getPurple());
        ((TextView) findViewById(R.id.tv_orange)).setText(category.getOrange());
        ((TextView) findViewById(R.id.tv_pink)).setText(category.getPink());
    }

    @Override
    public void setSchedule(CalendarSchedule schedule) {
        switch (schedule.getCategory()) {
            case "purple":
                upState = R.id.view_first;
                viewFirst.startAnimation(animBig);
                break;
            case "blue":
                upState = R.id.view_second;
                viewSecond.startAnimation(animBig);
                break;
            case "pink":
                upState = R.id.view_third;
                viewThird.startAnimation(animBig);
                break;
            case "orange":
                upState = R.id.view_fourth;
                viewFourth.startAnimation(animBig);
        }

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
