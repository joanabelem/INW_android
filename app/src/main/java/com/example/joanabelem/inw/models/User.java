package com.example.joanabelem.inw.models;

import com.google.gson.annotations.SerializedName;

public class User {

    private String name;
    private String email;
    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("followers_url")
    private String followersUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_url() {
        return avatarUrl;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatarUrl = avatar_url;
    }

    public String getFollowers_url() {
        return followersUrl;
    }

    public void setFollowers_url(String followers_url) {
        this.followersUrl = followers_url;
    }
}
