package com.simplecode.githubapp.service;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String BASE_URL = "https://api.github.com";

    private static RestClient restClient;

    public static RestClient getInstance() {
        if (restClient == null)
            restClient = new RestClient();

        return restClient;
    }

    private final Retrofit retrofit;

    private GithubService githubService;

    private RestClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public GithubService getGithubService() {
        if (githubService == null)
            githubService = retrofit.create(GithubService.class);

        return githubService;
    }
}
