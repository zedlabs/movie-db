package com.zed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String overview = intent.getStringExtra("overview");
        boolean adult = intent.getBooleanExtra("adult", true);
        String year = intent.getStringExtra("year");
        int rating = intent.getIntExtra("rating", 0);
        String posterUrl = intent.getStringExtra("poster_url");



        Log.e("main2",title+" "+overview+adult+year+rating+posterUrl );
    }
}
