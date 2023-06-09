package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.UTILIS.Credentials;

public class detailfilm extends AppCompatActivity {
    private ImageView poster,latarposter,back;
    private TextView title,releaseDate,rating,overview;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfilm);

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
                Intent intent = new Intent(detailfilm.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }
    private void getMovieDetail() {
        if (getIntent().hasExtra("movie")) {
            MovieModel model = getIntent().getParcelableExtra("movie");

            // string text
            String getRating = String.valueOf(model.getVote_average());

            title.setText(model.getTitle());
            releaseDate.setText(model.getRelease_date());
            overview.setText(model.getOverview());
            rating.setText(getRating);

            // poster
            Glide.with(this).load(Credentials.Poster_URL + model.getPoster_path()).into(poster);
            // latar poster
            Glide.with(this).load(Credentials.Poster_URL + model.getBackdrop_path()).into(latarposter);
        }
    }
}