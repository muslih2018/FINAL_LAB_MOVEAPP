package com.example.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.ADPATERNYA.MovieRecycleView;
import com.example.fragment.ADPATERNYA.OnMovieListener;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.viewmodels.PopularMovieListViewModel;


public class FirstFragment extends Fragment implements OnMovieListener {

    private PopularMovieListViewModel popularMovieListViewModel;
    private RecyclerView recyclerView;
    private MovieRecycleView recycleViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        popularMovieListViewModel = new ViewModelProvider(this).get(PopularMovieListViewModel.class);

        // data observer
        ObservasingAnyChangesPopularMovie();

        // show popular movies
        popularMovieListViewModel.getPopularMovie(1);

        // configuring recycleview
        configureRecycleView();
        return view;
    }
    // init recycleView and adding data to it
    private void configureRecycleView() {
        recycleViewAdapter = new MovieRecycleView(this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void ObservasingAnyChangesPopularMovie() {
        popularMovieListViewModel.getPopularMovie().observe(getViewLifecycleOwner(), movieModels -> {
            // observing any data changes
            if (movieModels != null) {
                for (MovieModel model : movieModels) {
                    // get data
                    recycleViewAdapter.setMovie(movieModels);
                }
            }
        });
    }



    @Override
    public void onMovieClick(int pos) {
        // here is going to detail movie that has clicked
        Intent intent = new Intent(getContext(),detailfilm.class);
        intent.putExtra("movie",recycleViewAdapter.getSelectedMovie(pos));
        startActivity(intent);
    }
}