package com.example.joanabelem.inw.presenters;

import android.content.Context;

import com.example.joanabelem.inw.R;
import com.example.joanabelem.inw.api.GitHubUsersApi;
import com.example.joanabelem.inw.models.User;
import com.example.joanabelem.inw.views.DetailsView;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter {

    private DetailsView view;
    private GitHubUsersApi usersApi;
    private Context context;
    private Gson gson = new Gson();

    public DetailsPresenter(DetailsView view, Context context) {
        this.view = view;
        this.usersApi = GitHubUsersApi.getInstance();
        this.context = context;
    }

    public void getFollowers(String username){
        usersApi.getUserFollowers(username, new Callback<List<User>>(){
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> followers = response.body();
                view.createTable(followers);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                view.showMessage(context.getString(R.string.getUserError));
            }
        });

        view.hideProgress();
    }

}
