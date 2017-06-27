package com.simplecode.githubapp.model;


import com.google.gson.annotations.SerializedName;

public class User {

    private long id;

    private String login;
    private String name;
    private String company;
    private String location;
    private String email;
    @SerializedName("html_url")
    private String profileUrl;
    @SerializedName("avatar_url")
    private String avatarUrl;

    private int followers;
    @SerializedName("public_repos")
    private int publicRepositories;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getPublicRepositories() {
        return publicRepositories;
    }

    public void setPublicRepositories(int publicRepositories) {
        this.publicRepositories = publicRepositories;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
