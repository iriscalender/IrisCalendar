package com.dsm.iriscalendar.base;

public interface BaseContract {
    interface Presenter<T> {
        void createView(T view);
        void destroyView();
    }

    interface View {
        void setPresenter(Presenter<?> presenter);
    }
}
