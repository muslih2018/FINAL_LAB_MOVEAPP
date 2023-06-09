package com.example.fragment.MODELNYA.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.MovieModel2;
import com.example.fragment.REPOSITORY.PopularMovieRepository;
import com.example.fragment.REPOSITORY.PopularMovieRepository2;

import java.util.List;

public class PopularMovieListViewModel2 extends ViewModel {
    private PopularMovieRepository2 popularMovieRepository2;

    public PopularMovieListViewModel2() {
        popularMovieRepository2 = PopularMovieRepository2.getInstance();
    }

    public LiveData<List<MovieModel2>> getPopularMovie2() {
        return popularMovieRepository2.getPopularMovie2();
    }

    public void getPopularMovie2(int page) {
        popularMovieRepository2.getPopularMovie2(page);
    }

    // next page
    public void popularMovieNextPage() {
        popularMovieRepository2.popularMovieNextPage();
    }
}
