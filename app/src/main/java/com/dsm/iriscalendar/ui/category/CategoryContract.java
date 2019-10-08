package com.dsm.iriscalendar.ui.category;

import com.dsm.iriscalendar.base.BaseContract;
import com.dsm.iriscalendar.data.model.Category;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface CategoryContract {
    interface View extends BaseContract.View {
        Category getCategories();

        void setCategory(Category category);

        void toastServerError();
        void toastModifySuccess();
        void toastInvalidValue();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCategory();
        void modifyCategory();
    }

    interface Repository {
        Flowable<Category> getCategory();
        Flowable<Response<Category>> modifyCategory(Category category);
    }
}
