package com.dsm.iriscalendar.base;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity implements BaseContract.View {

    private BaseContract.Presenter<?> presenter;

    @Override
    public void setPresenter(BaseContract.Presenter<?> presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.destroyView();
        }
        super.onDestroy();
    }
}
