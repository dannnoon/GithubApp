package com.simplecode.githubapp.ui.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simplecode.githubapp.R;
import com.simplecode.githubapp.model.User;
import com.simplecode.githubapp.ui.base.BaseActivity;
import com.simplecode.githubapp.ui.users.adapter.UsersAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersActivity extends BaseActivity implements UsersView {

    @BindView(R.id.users_recycler_view)
    protected RecyclerView usersRecyclerView;

    private UsersPresenter presenter;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initPresenter();
        initView();
        initUserList();

        presenter.getUserList();
    }

    private void initView() {
        setTitle(getString(R.string.users));
    }

    private void initPresenter() {
        presenter = new UsersPresenterImpl();
        presenter.attachView(this);
    }

    private void initUserList() {
        usersAdapter = new UsersAdapter();

        usersRecyclerView.setAdapter(usersAdapter);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void fillViewWithUsers(List<User> users) {
        usersAdapter.setUsers(users);
    }

    @Override
    protected void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }
}
