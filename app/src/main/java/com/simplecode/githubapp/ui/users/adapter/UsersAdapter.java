package com.simplecode.githubapp.ui.users.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplecode.githubapp.R;
import com.simplecode.githubapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter {

    private static final int USER_ROW = 1;

    private List<User> users;

    public UsersAdapter() {
        users = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case USER_ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_row, parent, false);
                return new UserRowViewHolder(view);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserRowViewHolder)
            ((UserRowViewHolder) holder).update(users.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return USER_ROW;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users.clear();
        this.users.addAll(users);

        notifyDataSetChanged();
    }
}
