package com.example.fragment.ADPATERNYA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.MovieModel2;
import com.example.fragment.R;
import com.example.fragment.UTILIS.Credentials;

import java.util.List;

public class MovieRecycleView2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel2> movieList;
    private final OnMovieListener2 onMovieListener2;

    public MovieRecycleView2(OnMovieListener2 onMovieListener2) {
        this.onMovieListener2 = onMovieListener2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list, parent, false);
        return new MovieViewHolder2(view,onMovieListener2);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder2)holder).title.setText(movieList.get(position).getName());
        ((MovieViewHolder2)holder).releaseDate.setText(movieList.get(position).getFirst_air_date());
        // poster
        Glide.with(holder.itemView.getContext()).load(Credentials.Poster_URL + movieList.get(position).getPoster_path()).into(((MovieViewHolder2) holder).poster);
    }

    @Override
    public int getItemCount() {
        if (movieList != null) {
            return movieList.size();
        } return 0;
    }

    public void setMovie(List<MovieModel2> list) {
        this.movieList = list;
        notifyDataSetChanged();
    }

    public MovieModel2 getSelectedMovie(int pos) {
        if (movieList != null) {
            if (movieList.size() > 0) {
                return movieList.get(pos);
            }
        } return null;
    }
}
