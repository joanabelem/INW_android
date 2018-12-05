package com.example.joanabelem.inw.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joanabelem.inw.R;
import com.example.joanabelem.inw.models.User;
import com.example.joanabelem.inw.presenters.DetailsPresenter;
import com.example.joanabelem.inw.views.DetailsView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements DetailsView {

    private User user;
    private Gson gson = new Gson();
    private ProgressBar spinner;
    private DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setInit(savedInstanceState);
    }

    private void setInit(Bundle savedInstanceState){
        spinner = findViewById(R.id.progressBar);
        detailsPresenter = new DetailsPresenter(this,DetailsActivity.this);

        if(savedInstanceState == null)
            user =  gson.fromJson(getIntent().getExtras().getString("user"), User.class);
        else
            user = gson.fromJson(savedInstanceState.getString("user"), User.class);

        TextView name = findViewById(R.id.name);
        name.setText(user.getName());

        TextView email = findViewById(R.id.email);
        email.setText(user.getEmail());

        ImageView userPhoto = findViewById(R.id.userPhoto);
        Picasso.with(DetailsActivity.this).load(user.getAvatar_url()).placeholder(R.drawable.avatar).into(userPhoto);

        detailsPresenter.getFollowers(user.getUsername());
        
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null)
            outState.putString("user", gson.toJson(user));
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }


    @Override
    public void hideProgress() {
        spinner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(DetailsActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void createTable(List<User> followers) {
        TableLayout table = findViewById(R.id.followersList);
        int i = 0;
        for (User u : followers) {
            TableRow row = new TableRow(this);
            View tableCell = DetailsActivity.this.getLayoutInflater().inflate(R.layout.table_followers_layout,
                    row, true);

            TextView name = tableCell.findViewById(R.id.followerName);
            name.setText(u.getUsername());

            ImageView userPhoto = tableCell.findViewById(R.id.followerPhoto);
            Picasso.with(DetailsActivity.this).load(u.getAvatar_url()).placeholder(R.drawable.avatar).into(userPhoto);

            table.addView(row, i);
            i++;
        }
    }
}
