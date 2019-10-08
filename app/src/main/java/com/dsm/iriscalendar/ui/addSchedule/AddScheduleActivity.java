package com.dsm.iriscalendar.ui.addSchedule;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
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

public class AddScheduleActivity extends BaseActivity implements AddScheduleContract.View {

    @BindView(R.id.tv_category)
    TextView tvCategory;

    @BindView(R.id.tv_cancel)
    TextView tvCancel;

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

    @BindView(R.id.tv_important)
    TextView tvImportant;

    @BindView(R.id.tv_complete)
    TextView tvComplete;

    private int upState;
    private boolean isImportant = false;

    @Inject
    AddScheduleContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);
        presenter.createView(this);
        animBig.setFillAfter(true);
        presenter.getCategory();

        upState = R.id.view_first;
        viewFirst.startAnimation(animBig);

        tvCancel.setOnClickListener(v -> finish());

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

        tvComplete.setOnClickListener(v -> presenter.addSchedule());
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
        ((TextView) findViewById(R.id.tv_purple)).setText(category.getPurple());
        ((TextView) findViewById(R.id.tv_blue)).setText(category.getBlue());
        ((TextView) findViewById(R.id.tv_pink)).setText(category.getPink());
        ((TextView) findViewById(R.id.tv_orange)).setText(category.getOrange());
    }
}
