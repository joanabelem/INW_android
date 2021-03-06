package com.example.joanabelem.inw.api;

import com.example.joanabelem.inw.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUsersInterface {

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("users/{username}/followers")
    Call<List<User>> getUserFollowers(@Path("username") String username);

}