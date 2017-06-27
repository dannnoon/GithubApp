package com.simplecode.githubapp.service;

import com.simplecode.githubapp.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("/users")
    Single<List<User>> listUsers();

    @GET("/users/{login}")
    Single<User> getUser(@Path("login") String login);
}
