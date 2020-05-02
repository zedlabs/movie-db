package com.zed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

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
        String rating = intent.getStringExtra("rating");
        String posterUrl = intent.getStringExtra("poster_url");

        TextView movieTitle = findViewById(R.id.movie_name_details);
        TextView movieYear = findViewById(R.id.detail_1);
        TextView movieAdult = findViewById(R.id.detail_2);
        TextView movieRating = findViewById(R.id.detail_3);
        TextView movieOverview = findViewById(R.id.detail_bottom);
        ImageView moviePoster = findViewById(R.id.movie_poster_details);

        movieTitle.setText(title);
        movieYear.setText(String.format("(%s)", year));
        movieAdult.setText(String.format("Adult: %s", adult));
        movieRating.setText(String.format("Rating : %s/10", String.valueOf(rating)));
        movieOverview.setText(overview);


        Glide.with(this)
                .load(posterUrl)
                .centerInside()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(moviePoster);


        Log.e("main2",title+" "+overview+adult+year+rating+posterUrl );
    }
}
