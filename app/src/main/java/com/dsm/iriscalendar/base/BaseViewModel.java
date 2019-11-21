package com.dsm.iriscalendar.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    private CompositeDisposable composite = new CompositeDisposable();

    public void addDisposable(Disposable disposable) {
        composite.add(disposable);
    }

    @Override
    protected void onCleared() {
        composite.clear();
        super.onCleared();
    }
}
