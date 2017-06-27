package com.simplecode.githubapp.ui.userdetails;

import com.simplecode.githubapp.model.User;

public interface UserDetailsView {
    void fillViewWithData(User user);

    void showLoadingScreen(boolean show);
}
