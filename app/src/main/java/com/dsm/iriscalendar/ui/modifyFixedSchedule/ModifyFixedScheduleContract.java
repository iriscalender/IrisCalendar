package com.dsm.iriscalendar.ui.modifyFixedSchedule;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.Category;

import io.reactivex.Flowable;

public interface ModifyFixedScheduleContract {
    interface View extends BaseContract.View {
        void setCategory(Category category);

        void toastServerError();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCategory();

        void getFixedSchedule();
    }

    interface Repository {
        Flowable<Category> getCategory();

//        Flowable<Category>
    }
}
