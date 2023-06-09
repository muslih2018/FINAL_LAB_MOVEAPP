package com.example.fragment.ADPATERNYA;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.R;

public class MovieViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title,releaseDate;
    ImageView poster;

    OnMovieListener2 onMovieListener2;

    public MovieViewHolder2(@NonNull View itemView, OnMovieListener2 onMovieListener2) {
        super(itemView);
        this.onMovieListener2 = onMovieListener2;

        title = itemView.findViewById(R.id.title);
        releaseDate = itemView.findViewById(R.id.releaseDate);
        poster = itemView.findViewById(R.id.poster);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        onMovieListener2.onMovieClick2(getPosition());
    }
}
