package com.dsm.iriscalendar.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private WeakReference<T> weakReference = null;

    public T view;

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void createView(T view) {
        weakReference = new WeakReference<>(view);
        this.view = weakReference.get();
        view.setPresenter(this);
    }

    @Override
    public void destroyView() {
        weakReference.clear();
        compositeDisposable.clear();
    }
}
