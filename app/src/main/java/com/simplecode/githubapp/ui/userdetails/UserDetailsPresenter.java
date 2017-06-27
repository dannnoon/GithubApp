package com.simplecode.githubapp.ui.userdetails;

import com.simplecode.githubapp.ui.base.BasePresenter;


public interface UserDetailsPresenter extends BasePresenter<UserDetailsView> {
    void getUserDetails(String login);
}
