package com.dsm.iriscalendar.ui.modifyFixedSchedule;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.data.model.Category;

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

        presenter.getCategory();
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
        ((TextView) findViewById(R.id.tv_purple)).setText(category.getPurple());
        ((TextView) findViewById(R.id.tv_blue)).setText(category.getBlue());
        ((TextView) findViewById(R.id.tv_pink)).setText(category.getPink());
        ((TextView) findViewById(R.id.tv_orange)).setText(category.getOrange());
    }

    @Override
    public void toastServerError() {
        Toast.makeText(this, R.string.error_server_error, Toast.LENGTH_SHORT).show();
    }
}
