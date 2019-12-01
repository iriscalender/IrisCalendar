package com.dsm.iriscalendar.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding> extends DaggerAppCompatActivity {

    public T binding;

    public abstract int getLayoutResourceId();

    public abstract void viewInit();

    public abstract void observeViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutResourceId());
        binding.setLifecycleOwner(this);

        ButterKnife.bind(this);

        viewInit();
        observeViewModel();
    }
}
