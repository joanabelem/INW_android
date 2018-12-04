package com.example.joanabelem.inw.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.joanabelem.inw.R;
import com.example.joanabelem.inw.models.User;
import com.example.joanabelem.inw.views.DetailsView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity implements DetailsView {

    private User user;
    private Gson gson = new Gson();
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setInit(savedInstanceState);
    }

    private void setInit(Bundle savedInstanceState){
        spinner = findViewById(R.id.progressBar);

        if(savedInstanceState == null)
            user =  gson.fromJson(getIntent().getExtras().getString("user"), User.class);
        else
            user = gson.fromJson(savedInstanceState.getString("user"), User.class);

        TextView name = findViewById(R.id.name);
        name.setText(user.getName());

        TextView email = findViewById(R.id.email);
        email.setText(user.getEmail());

        ImageView userPhoto = findViewById(R.id.userPhoto);
        Picasso.with(DetailsActivity.this).load(user.getAvatar_url()).into(userPhoto);
        
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
}
