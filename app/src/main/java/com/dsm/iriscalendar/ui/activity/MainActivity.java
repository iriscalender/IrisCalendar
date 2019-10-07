package com.dsm.iriscalendar.ui.activity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.Schedule;
import com.dsm.iriscalendar.ui.adapter.ScheduleListAdapter;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetActivity;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class MainActivity extends AppCompatActivity implements CalendarView.OnCalendarSelectListener {

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

    private boolean isOpen = false;

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

        tvReTimeSet.setOnClickListener(v -> startActivity(new Intent(this, ReTimeSetActivity.class)));

        tvModifyCategory.setOnClickListener(v -> startActivity(new Intent(this, ModifyCategoryActivity.class)));

        tvAddSchedule.setOnClickListener(v -> startActivity(new Intent(this, AddScheduleActivity.class)));

        List<Schedule> listItems = new ArrayList<>();
        listItems.add(new Schedule("title", "time"));
        listItems.add(new Schedule("title2", "time2"));
        listItems.add(new Schedule("title3", "time3"));
        listItems.add(new Schedule("title4", "time4"));
        listItems.add(new Schedule("title5", "time5"));
        listItems.add(new Schedule("title6", "time6"));
        listItems.add(new Schedule("title7", "time7"));
        rvSchedule.setAdapter(new ScheduleListAdapter(listItems));

        int year = calendarView.getCurYear();
        int month = calendarView.getCurMonth();

        calendarView.setOnCalendarSelectListener(this);

        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25).toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138).toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356).toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d).toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d).toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44).toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0).toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0).toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0).toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0));

        calendarView.setSchemeDate(map);
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

        if (!isOpen) {
            int x = layoutMain.getLeft();
            int y = layoutMain.getTop();

            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutMenu, x, y, startRadius, endRadius);

            layoutMenu.setVisibility(View.VISIBLE);
            anim.start();
            layoutMain.setVisibility(View.INVISIBLE);

            isOpen = true;

        } else {
            int x = layoutMenu.getLeft();
            int y = layoutMenu.getTop();

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
        tvToday.setText("Today " + calendar.getMonth() + " " + calendar.getDay());
    }
}
