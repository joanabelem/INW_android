package com.example.joanabelem.inw.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.joanabelem.inw.R;
import com.example.joanabelem.inw.presenters.SearchPresenter;
import com.example.joanabelem.inw.views.SearchView;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private ProgressBar spinner;
    private SearchPresenter searchPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        onClickSearchButton();
        init();
    }

    private void init(){
        spinner = findViewById(R.id.progressBar);
        searchPresenter = new SearchPresenter(this, SearchActivity.this);
    }

    private void onClickSearchButton() {

        final ImageView search = findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.username);
                searchPresenter.onClickSearchButton(username.getText().toString());
            }
        });
    }

    @Override
    public void showProgress() {
        System.out.println("show");
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        System.out.println("hide");
        spinner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(SearchActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
