package com.simplecode.githubapp.ui.users.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplecode.githubapp.R;
import com.simplecode.githubapp.model.User;
import com.simplecode.githubapp.ui.userdetails.UserDetailsActivity;
import com.simplecode.githubapp.util.BundleConstants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserRowViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.info_icon)
    protected ImageView infoIcon;
    @BindView(R.id.user_icon)
    protected ImageView userIcon;
    @BindView(R.id.user_login)
    protected TextView userLoginTextView;

    private Context context;

    public UserRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.context = itemView.getContext();
    }

    public void update(User user) {
        setInfoIcon(user.getLogin());
        setUserIcon(user.getAvatarUrl());
        setUserName(user.getLogin());
    }

    private void setInfoIcon(String login) {
        int color = ContextCompat.getColor(context, R.color.colorPrimary);

        infoIcon.getDrawable().mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        infoIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserDetailsActivity.class);
            intent.putExtra(BundleConstants.LOGIN, login);
            context.startActivity(intent);
        });
    }

    private void setUserIcon(String iconUrl) {
        Picasso.with(context).load(iconUrl).placeholder(R.drawable.ic_account).into(userIcon);
    }

    private void setUserName(String login) {
        userLoginTextView.setText(login);
    }
}
