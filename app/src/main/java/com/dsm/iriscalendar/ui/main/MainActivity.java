package com.dsm.iriscalendar.ui.main;

import android.animation.Animator;
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
import com.dsm.iriscalendar.databinding.ActivityMainBinding;
import com.dsm.iriscalendar.ui.adapter.ScheduleListAdapter;
import com.dsm.iriscalendar.ui.addFixedSchedule.AddFixedScheduleActivity;
import com.dsm.iriscalendar.ui.addSchedule.AddScheduleActivity;
import com.dsm.iriscalendar.ui.category.CategoryActivity;
import com.dsm.iriscalendar.ui.dialog.LogoutDialog;
import com.dsm.iriscalendar.ui.reTimeSet.ReTimeSetActivity;
import com.dsm.iriscalendar.util.TimeUtil;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.text.DateFormatSymbols;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @BindView(R.id.rv_schedule) RecyclerView rvSchedule;

    @BindView(R.id.layoutMain) ConstraintLayout layoutMain;

    @BindView(R.id.layoutMenu) ConstraintLayout layoutMenu;

    @BindView(R.id.layoutAdd) ConstraintLayout layoutAdd;

    @BindView(R.id.calendarView) CalendarView calendarView;

    @Inject MainViewModel viewModel;

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
        binding.setViewModel(viewModel);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public void viewInit() {
        findViewById(R.id.tv_re_time_set).setOnClickListener(v -> startActivity(new Intent(this, ReTimeSetActivity.class)));

        findViewById(R.id.tv_modify_category).setOnClickListener(v -> startActivity(new Intent(this, CategoryActivity.class)));

        findViewById(R.id.tv_add_schedule).setOnClickListener(v -> startActivity(new Intent(this, AddScheduleActivity.class)));

        findViewById(R.id.tv_add_fixed_schedule).setOnClickListener(v -> startActivity(new Intent(this, AddFixedScheduleActivity.class)));

        findViewById(R.id.tv_logout).setOnClickListener(v -> new LogoutDialog().show(getSupportFragmentManager(), ""));

        findViewById(R.id.iv_left).setOnClickListener(v -> calendarView.scrollToPre(true));

        findViewById(R.id.iv_right).setOnClickListener(v -> calendarView.scrollToNext(true));

        ((TextView) findViewById(R.id.tv_calendar_month)).setText(new DateFormatSymbols().getMonths()[calendarView.getCurMonth() - 1]);

        rvSchedule.setAdapter(adapter);

        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                viewModel.selectedDate.setValue(TimeUtil.formatToFullDate(calendar.getYear(), calendar.getMonth(), calendar.getDay()));
            }
        });

        viewModel.selectedDate.setValue(TimeUtil.formatToFullDate(calendarView.getCurYear(), calendarView.getCurMonth(), calendarView.getCurDay()));

        viewModel.getCalendarBook();
    }

    @Override
    public void observeViewModel() {
        viewModel.calendarSchedule.observe(this, calendarSchedules -> adapter.setItems(calendarSchedules));

        viewModel.selectedDate.observe(this, date -> viewModel.getCalendarSchedule());

        viewModel.getToastEvent().observe(this, stringId -> Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show());
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
}
