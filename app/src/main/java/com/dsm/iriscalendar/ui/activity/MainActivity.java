package com.dsm.iriscalendar.ui.activity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.data.model.CalendarBook;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.ui.adapter.ScheduleListAdapter;
import com.dsm.iriscalendar.ui.addFixedSchedule.AddFixedScheduleActivity;
import com.dsm.iriscalendar.ui.addSchedule.AddScheduleActivity;
import com.dsm.iriscalendar.ui.category.CategoryActivity;
import com.dsm.iriscalendar.ui.dialog.LogoutDialog;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetActivity;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class MainActivity extends BaseActivity implements CalendarView.OnCalendarSelectListener, MainContract.View {

    @BindView(R.id.rv_schedule)
    RecyclerView rvSchedule;

    @BindView(R.id.layoutMain)
    ConstraintLayout layoutMain;

    @BindView(R.id.layoutMenu)
    ConstraintLayout layoutMenu;

    @BindView(R.id.layoutAdd)
    ConstraintLayout layoutAdd;

    @BindView(R.id.tv_re_time_set)
    TextView tvReTimeSet;

    @BindView(R.id.tv_modify_category)
    TextView tvModifyCategory;

    @BindView(R.id.tv_add_schedule)
    TextView tvAddSchedule;

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    @BindView(R.id.tv_today)
    TextView tvToday;

    @BindView(R.id.tv_add_fixed_schedule)
    TextView tvAddFixedSchedule;

    @Inject
    MainContract.Presenter presenter;

    private boolean isOpen = false;

    private ScheduleListAdapter adapter = new ScheduleListAdapter();

    private Subject<Long> backSubject = BehaviorSubject.createDefault(0L).toSerialized();

    private Disposable backSubjectDisposable = backSubject.buffer(2, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(it -> {
                if (it.get(1) - it.get(0) <= 1500)
                    finish();
                else
                    Toast.makeText(this, getString(R.string.back_to_exit), Toast.LENGTH_SHORT).show();
            });

    @Override
    public void onBackPressed() {
        backSubject.onNext(System.currentTimeMillis());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.createView(this);
        presenter.getCalendarBook();

        tvReTimeSet.setOnClickListener(v -> startActivity(new Intent(this, ReTimeSetActivity.class)));

        tvModifyCategory.setOnClickListener(v -> startActivity(new Intent(this, CategoryActivity.class)));

        tvAddSchedule.setOnClickListener(v -> startActivity(new Intent(this, AddScheduleActivity.class)));

        tvAddFixedSchedule.setOnClickListener(v -> startActivity(new Intent(this, AddFixedScheduleActivity.class)));

        findViewById(R.id.tv_logout).setOnClickListener(v -> new LogoutDialog().show(getSupportFragmentManager(), ""));

        findViewById(R.id.iv_left).setOnClickListener(v -> calendarView.scrollToPre());

        findViewById(R.id.iv_right).setOnClickListener(v -> calendarView.scrollToNext());

        rvSchedule.setAdapter(adapter);

        ((TextView) findViewById(R.id.tv_calendar_month)).setText(new DateFormatSymbols().getMonths()[calendarView.getCurMonth() - 1]);

        presenter.getCalendarSchedule(new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(new Date(System.currentTimeMillis())));

        calendarView.setOnCalendarSelectListener(this);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);
        return calendar;
    }

    public void viewMenu(View view) {

        int x = layoutMain.getLeft();
        int y = layoutMain.getTop();

        if (!isOpen) {
            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutMenu, x, y, startRadius, endRadius);
            anim.setDuration(500);

            layoutMenu.setVisibility(View.VISIBLE);
            anim.start();
            layoutMain.setVisibility(View.INVISIBLE);

            isOpen = true;

        } else {
            int startRadius = Math.max(layoutMain.getWidth(), layoutMain.getHeight());
            int endRadius = 0;

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutMenu, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutMenu.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.setDuration(500);
            anim.start();
            layoutMain.setVisibility(View.VISIBLE);

            isOpen = false;
        }
    }

    public void viewAdd(View view) {
        if (!isOpen) {
            int x = layoutMain.getRight();
            int y = layoutMain.getTop();

            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutAdd, x, y, startRadius, endRadius);
            anim.setDuration(500);

            layoutAdd.setVisibility(View.VISIBLE);
            anim.start();
            layoutMain.setVisibility(View.INVISIBLE);

            isOpen = true;
        } else {
            int x = layoutAdd.getRight();
            int y = layoutAdd.getTop();

            int startRadius = Math.max(layoutMain.getWidth(), layoutMain.getHeight());
            int endRadius = 0;

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutAdd, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutAdd.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.setDuration(500);
            anim.start();
            layoutMain.setVisibility(View.VISIBLE);

            isOpen = false;
        }
    }

    @Override
    protected void onDestroy() {
        backSubjectDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        ((TextView) findViewById(R.id.tv_calendar_month)).setText(new DateFormatSymbols().getMonths()[calendar.getMonth() - 1]);
        tvToday.setText(calendar.getMonth() + " " + calendar.getDay());
        String date = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
        try {
            Date tempDate = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse(date);
            assert tempDate != null;
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(tempDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        presenter.getCalendarSchedule(date);
    }

    @Override
    public void toastServerError() {
        Toast.makeText(this, R.string.error_server_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCalendarBook(List<CalendarBook> calendarBook) {
        Map<String, Calendar> result = new HashMap<>();

        for (CalendarBook calendar : calendarBook) {
            String calendarDate = calendar.getDate();
            String year = calendarDate.split("-")[0];
            String month = calendarDate.split("-")[1];
            String date = calendarDate.split("-")[2];

            int color = 0;
            switch (calendar.getCategory()) {
                case "purple":
                    color = 0xFF7247B2;
                    break;
                case "blue":
                    color = 0xFF3CB8EF;
                    break;
                case "pink":
                    color = 0xFFD92D73;
                    break;
                case "orange":
                    color = 0xFFFAA86B;
                    break;
            }
            Calendar mapValue = getSchemeCalendar(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(date), color);
            result.put(mapValue.toString(), mapValue);
        }

        calendarView.setSchemeDate(result);
    }

    @Override
    public void setCalendarSchedule(List<CalendarSchedule> calendarSchedule) {
        adapter.setItems(calendarSchedule);
    }
}
