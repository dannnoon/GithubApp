package com.simplecode.githubapp.ui.userdetails;

import android.text.TextUtils;

import com.simplecode.githubapp.service.GithubService;
import com.simplecode.githubapp.service.RestClient;

import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserDetailsPresenterImpl implements UserDetailsPresenter {

    private WeakReference<UserDetailsView> view;
    private CompositeDisposable disposable;

    private GithubService githubService;

    public UserDetailsPresenterImpl() {
        githubService = RestClient.getInstance().getGithubService();
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachView(UserDetailsView view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void dispose() {
        view = null;

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void getUserDetails(String login) {
        if (TextUtils.isEmpty(login))
            return;

        if (view.get() != null) {
            view.get().showLoadingScreen(true);
        }

        disposable.add(
                githubService.getUser(login)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((user, throwable) -> {
                            if (view.get() != null) {
                                if (user != null)
                                    view.get().fillViewWithData(user);

                                view.get().showLoadingScreen(false);
                            }
                        })
        );
    }
}
