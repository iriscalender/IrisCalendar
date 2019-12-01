package com.dsm.iriscalendar.ui.category;

import android.util.Log;

import com.dsm.iriscalendar.base.BasePresenter;

public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {

    private CategoryContract.Repository repository;

    public CategoryPresenter(CategoryContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void getCategory() {
        addDisposable(
                repository.getCategory().subscribe(
                        response -> view.setCategory(response),
                        throwable -> {
                            view.toastServerError();
                            Log.d("DEBUGLOG", throwable.getMessage());
                        }
                )
        );
    }

    @Override
    public void modifyCategory() {
        addDisposable(
                repository.modifyCategory(view.getCategories())
                        .subscribe(response -> {
                            switch (response.code()) {
                                case 200:
                                    view.toastModifySuccess();
                                    break;
                                case 400:
                                    view.toastInvalidValue();
                                    break;
                                default:
                                    view.toastServerError();
                            }
                        }, throwable -> view.toastServerError())
        );
    }
}
