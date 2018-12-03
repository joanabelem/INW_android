package com.example.joanabelem.inw.api;
import com.example.joanabelem.inw.models.User;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;

public class GitHubUsersApi {

    private final GitHubUsersInterface gitUserInterface;
    private static GitHubUsersApi usersApi;

    private GitHubUsersApi(){
        gitUserInterface = Api.getClient().create(GitHubUsersInterface.class);
    }

    public static GitHubUsersApi getInstance(){
        if(usersApi == null)
            return usersApi = new GitHubUsersApi();
        return usersApi;
    }

    public void getUser(String username, Callback<User> callback) {
        Call<User> call = gitUserInterface.getUser(username);
        call.enqueue(callback);
    }

    public void getUserFollowers(String username, Callback<User> callback) {
        Call<User> call = gitUserInterface.getUserFollowers(username);
        call.enqueue(callback);
    }

}