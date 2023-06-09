package com.example.fragment.REPOSITORY;

import androidx.lifecycle.LiveData;

import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.MovieModel2;
import com.example.fragment.REQUEST.PopularMovieApiClient;
import com.example.fragment.REQUEST.PopularMovieApiClient2;

import java.util.List;

public class PopularMovieRepository2 {
    private static PopularMovieRepository2 instance;
    private PopularMovieApiClient2 popularMovieApiClient2;

    private int page;

    public static PopularMovieRepository2 getInstance() {
        if (instance == null) {
            instance = new PopularMovieRepository2();
        }

        return instance;
    }

    private PopularMovieRepository2() {
        popularMovieApiClient2 = PopularMovieApiClient2.getInstance();
    }

    public LiveData<List<MovieModel2>> getPopularMovie2() {
        return popularMovieApiClient2.getPopularMovie2();
    }

    public void getPopularMovie2(int page) {
        this.page = page;
        popularMovieApiClient2.getPopularMovie2(page);
    }

    // next page
    public void popularMovieNextPage() {
        getPopularMovie2(page+1);
    }
}
