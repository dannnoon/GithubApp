package com.simplecode.githubapp.ui.users;

import com.simplecode.githubapp.model.User;

import java.util.List;

public interface UsersView {
    void fillViewWithUsers(List<User> users);
}
