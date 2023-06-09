package com.example.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.MovieModel2;
import com.example.fragment.UTILIS.Credentials;

public class detailfilm2 extends AppCompatActivity {
    private ImageView poster,latarposter,back;
    private TextView title,releaseDate,rating,overview;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfilm2);

        title = findViewById(R.id.titleDetail);
        overview = findViewById(R.id.overview);
        releaseDate = findViewById(R.id.releaseDateDetail);
        rating = findViewById(R.id.ratingTextDetail);
        poster = findViewById(R.id.poster);
        latarposter=findViewById(R.id.latarposter);
        back=findViewById(R.id.back);
        getMovieDetail();

//        kembali
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void getMovieDetail() {
        if (getIntent().hasExtra("movie")) {
            MovieModel2 model = getIntent().getParcelableExtra("movie");

            // string text
            String getRating = String.valueOf(model.getVote_average());

            title.setText(model.getName());
            releaseDate.setText(model.getFirst_air_date());
            overview.setText(model.getOverview());
            rating.setText(getRating);

            // poster
            Glide.with(this).load(Credentials.Poster_URL + model.getPoster_path()).into(poster);
            // latar poster
            Glide.with(this).load(Credentials.Poster_URL + model.getBackdrop_path()).into(latarposter);
        }
    }
}
