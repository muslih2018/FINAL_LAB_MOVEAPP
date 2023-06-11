package com.example.fragment.ADPATERNYA;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fragment.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title,releaseDate;
    ImageView poster;

    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;

        title = itemView.findViewById(R.id.title);
        releaseDate = itemView.findViewById(R.id.releaseDate);
        poster = itemView.findViewById(R.id.poster);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getPosition());
    }
}
