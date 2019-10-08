package com.dsm.iriscalendar.ui.addSchedule;

import com.dsm.iriscalendar.base.BasePresenter;

public class AddSchedulePresenter extends BasePresenter<AddScheduleContract.View> implements AddScheduleContract.Presenter {

    private AddScheduleContract.Repository repository;

    public AddSchedulePresenter(AddScheduleContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void addSchedule() {
        String category = view.getCategory();
        String todo = view.getTodo();
        String endTime = view.getEndTime();
        int requirementTime = view.getRequiredTime();
        boolean isParticularImportant = view.getIsParticularImportant();

        if (category.isEmpty() || todo.isEmpty() || endTime.isEmpty() || requirementTime == 0) {
            view.toastBlankError();
            return;
        }

        addDisposable(
                repository.addSchedule(category, todo, endTime, requirementTime, isParticularImportant)
                        .subscribe(response -> {
                            switch (response.code()) {
                                case 200:
                                    view.finishActivity();
                                    break;
                                case 400:
                                    view.toastInvalidValue();
                                    break;
                                case 409:
                                    view.toastImpossibleSchedule();
                                    break;
                                default:
                                    view.toastServerError();
                            }
                        }, throwable -> view.toastServerError())
        );
    }

    @Override
    public void getCategory() {
        addDisposable(
                repository.getCategory().subscribe(
                        response -> view.setCategory(response),
                        throwable -> view.toastServerError()
                )
        );
    }
}
