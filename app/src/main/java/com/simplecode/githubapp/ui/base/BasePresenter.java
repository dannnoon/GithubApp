package com.simplecode.githubapp.ui.base;

public interface BasePresenter<T> {
    void attachView(T view);

    void dispose();
}
