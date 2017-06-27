package com.simplecode.githubapp.ui.users;

import com.simplecode.githubapp.service.GithubService;
import com.simplecode.githubapp.service.RestClient;

import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UsersPresenterImpl implements UsersPresenter {

    private WeakReference<UsersView> view;
    private CompositeDisposable disposable;

    private GithubService githubService;

    public UsersPresenterImpl() {
        disposable = new CompositeDisposable();
        githubService = RestClient.getInstance().getGithubService();
    }

    @Override
    public void attachView(UsersView view) {
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
    public void getUserList() {
        disposable.add(
                githubService.listUsers()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((users, throwable) -> {
                            if (view.get() != null && users != null) {
                                view.get().fillViewWithUsers(users);
                            }
                        })
        );
    }

}
