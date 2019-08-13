package com.dsm.iriscalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

    private boolean isOpen = false;

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
}
