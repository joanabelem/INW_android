package com.example.joanabelem.inw.presenters;

import android.content.Context;
import android.content.Intent;

import com.example.joanabelem.inw.R;
import com.example.joanabelem.inw.activities.DetailsActivity;
import com.example.joanabelem.inw.api.GitHubUsersApi;
import com.example.joanabelem.inw.models.User;
import com.example.joanabelem.inw.views.SearchView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {

    private SearchView view;
    private GitHubUsersApi usersApi;
    private Context context;
    private Gson gson = new Gson();

    public SearchPresenter(SearchView view, Context context) {
        this.view = view;
        this.usersApi = GitHubUsersApi.getInstance();
        this.context = context;
    }

    public void onClickSearchButton(String username){

        this.view.showProgress();

        if(username.length() == 0) {
            this.view.showMessage((context.getString(R.string.emptyUserError)));
            this.view.hideProgress();
            return;
        }

        usersApi.getUser(username, new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user == null) {
                    view.showMessage(context.getString(R.string.notExistUserError));
                    view.hideProgress();
                }else{
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("user", gson.toJson(user));
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.showMessage(context.getString(R.string.getUserError));
            }
        });

        this.view.hideProgress();

    }
}
