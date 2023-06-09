package com.example.fragment.ADPATERNYA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.R;
import com.example.fragment.UTILIS.Credentials;

import java.util.List;

public class MovieRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> movieList;
    private final OnMovieListener onMovieListener;

    public MovieRecycleView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list, parent, false);
        return new MovieViewHolder(view,onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder)holder).title.setText(movieList.get(position).getTitle());
        ((MovieViewHolder)holder).releaseDate.setText(movieList.get(position).getRelease_date());
        // poster
        Glide.with(holder.itemView.getContext()).load(Credentials.Poster_URL + movieList.get(position).getPoster_path()).into(((MovieViewHolder) holder).poster);
    }

    @Override
    public int getItemCount() {
        if (movieList != null) {
            return movieList.size();
        } return 0;
    }

    public void setMovie(List<MovieModel> list) {
        this.movieList = list;
        notifyDataSetChanged();
    }

    public MovieModel getSelectedMovie(int pos) {
        if (movieList != null) {
            if (movieList.size() > 0) {
                return movieList.get(pos);
            }
        } return null;
    }
}
