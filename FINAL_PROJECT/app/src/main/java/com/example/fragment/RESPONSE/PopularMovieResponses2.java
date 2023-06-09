package com.example.fragment.RESPONSE;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.MovieModel2;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMovieResponses2 {
    @SerializedName("results")
    @Expose
    private List<MovieModel2> movies;

    public List<MovieModel2> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieResponses{" +
                "movies=" + movies +
                '}';
    }
}
