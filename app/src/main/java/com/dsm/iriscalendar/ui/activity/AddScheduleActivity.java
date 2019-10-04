package com.dsm.iriscalendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dsm.iriscalendar.R;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddScheduleActivity extends AppCompatActivity {

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

    private int upState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);
        animBig.setFillAfter(true);

        tvCancel.setOnClickListener(v -> finish());
    }

    public void onClickCategory(View v) {
        int clickedView = v.getId();

        if (clickedView == upState) {
            return;
        }

        switch (upState) {
            case R.id.view_first: viewFirst.startAnimation(animSmall); break;
            case R.id.view_second: viewSecond.startAnimation(animSmall); break;
            case R.id.view_third: viewThird.startAnimation(animSmall); break;
            case R.id.view_fourth: viewFourth.startAnimation(animSmall); break;
        }

        if (clickedView == R.id.view_first) {
            viewFirst.startAnimation(animBig);
            upState = R.id.view_first;
        } else if (clickedView == R.id.view_second) {
            viewSecond.startAnimation(animBig);
            upState = R.id.view_second;
        } else if(clickedView == R.id.view_third) {
            viewThird.startAnimation(animBig);
            upState = R.id.view_third;
        } else if(clickedView == R.id.view_fourth) {
            viewFourth.startAnimation(animBig);
            upState = R.id.view_fourth;
        }
    }
}
