package com.dsm.iriscalendar.ui.modifyFixedSchedule;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ModifyFixedScheduleActivity extends BaseActivity implements ModifyFixedScheduleContract.View {

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

    @Inject
    ModifyFixedScheduleContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_fixed_schedule);
        ButterKnife.bind(this);
        presenter.createView(this);
        animBig.setFillAfter(true);
        upState = R.id.view_first;
        viewFirst.startAnimation(animBig);
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
}
