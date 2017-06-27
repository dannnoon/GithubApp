package com.simplecode.githubapp.ui.userdetails;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simplecode.githubapp.R;
import com.simplecode.githubapp.model.User;
import com.simplecode.githubapp.ui.base.BaseActivity;
import com.simplecode.githubapp.util.BundleConstants;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends BaseActivity implements UserDetailsView {

    @BindView(R.id.user_icon)
    protected ImageView userIcon;

    @BindView(R.id.user_information_container)
    protected RelativeLayout container;

    @BindView(R.id.user_name)
    protected TextView userNameTextView;
    @BindView(R.id.user_location)
    protected TextView userLocationTextView;
    @BindView(R.id.user_followers)
    protected TextView userFollowersTextView;

    @BindView(R.id.send_email_button)
    protected Button sendEmailButton;
    @BindView(R.id.visit_profile_button)
    protected Button visitProfileButton;

    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;

    private UserDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initPresenter();
        initView();
        initData();
    }

    private void initPresenter() {
        presenter = new UserDetailsPresenterImpl();
        presenter.attachView(this);
    }

    private void initView() {
        setTitle(getString(R.string.user_details));
        showBackButton(true);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            presenter.getUserDetails(bundle.getString(BundleConstants.LOGIN));
        } else {
            finish();
        }
    }

    @Override
    public void fillViewWithData(User user) {
        Picasso.with(this).load(user.getAvatarUrl()).placeholder(R.drawable.ic_account).into(userIcon);

        setSubtitle(user.getLogin());
        setButtons(user.getEmail(), user.getProfileUrl());
        setInformation(user.getName(), user.getLocation(), user.getFollowers());
    }

    @Override
    public void showLoadingScreen(boolean show) {
        setVisibility(progressBar, show);
        setVisibility(container, !show);
    }

    private void setButtons(String email, String url) {
        if (setVisibility(sendEmailButton, !TextUtils.isEmpty(email))) {
            sendEmailButton.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, email);

                startActivity(Intent.createChooser(intent, getString(R.string.send_email)));
            });
        }

        if (setVisibility(visitProfileButton, !TextUtils.isEmpty(url))) {
            visitProfileButton.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));

                startActivity(intent);
            });
        }
    }

    private void setInformation(String name, String location, int followers) {
        if (setVisibility(userNameTextView, !TextUtils.isEmpty(name))) {
            userNameTextView.setText(name);
        }

        if (setVisibility(userLocationTextView, !TextUtils.isEmpty(location))) {
            userLocationTextView.setText(location);
        }

        userFollowersTextView.setText(String.format(Locale.getDefault(), "%d %s", followers,
                getString(R.string.followers)));
    }

    private boolean setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return visible;
    }

    @Override
    protected void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }

}
