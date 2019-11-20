package com.dsm.iriscalendar.ui.modifyFixedSchedule;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.ui.custom.CategoryView;

import javax.inject.Inject;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ModifyFixedScheduleActivity extends BaseActivity implements ModifyFixedScheduleContract.View {

    @BindView(R.id.cv_modify_fixed_schedule) CategoryView cvModifyFixedSchedule;

    @Inject ModifyFixedScheduleContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_fixed_schedule);
        ButterKnife.bind(this);
        presenter.createView(this);

        presenter.getCategory();
    }

    @Override
    public void setCategory(Category category) {
        cvModifyFixedSchedule.setCategory(category);
    }

    @Override
    public void toastServerError() {
        Toast.makeText(this, R.string.error_server_error, Toast.LENGTH_SHORT).show();
    }
}
