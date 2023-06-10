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
import com.example.fragment.ADPATERNYA.MovieRecycleView2;
import com.example.fragment.ADPATERNYA.OnMovieListener;
import com.example.fragment.ADPATERNYA.OnMovieListener2;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.MovieModel2;
import com.example.fragment.MODELNYA.viewmodels.PopularMovieListViewModel;
import com.example.fragment.MODELNYA.viewmodels.PopularMovieListViewModel2;


public class fragmentprofil extends Fragment implements OnMovieListener2 {

    private PopularMovieListViewModel2 popularMovieListViewModel2;
    private RecyclerView recyclerView2;
    private MovieRecycleView2 recycleViewAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentprofil, container, false);

        recyclerView2 = view.findViewById(R.id.recyclerview2);
        popularMovieListViewModel2 = new ViewModelProvider(this).get(PopularMovieListViewModel2.class);

        // data observer
        ObservasingAnyChangesPopularMovie();

        // show popular movies
        popularMovieListViewModel2.getPopularMovie2(1);

        // configuring recycleview
        configureRecycleView();
        return view;
    }
    private void configureRecycleView() {
        recycleViewAdapter2 = new MovieRecycleView2(this);
        recyclerView2.setAdapter(recycleViewAdapter2);
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void ObservasingAnyChangesPopularMovie() {
        popularMovieListViewModel2.getPopularMovie2().observe(getViewLifecycleOwner(), movieModels -> {
            // observing any data changes
            if (movieModels != null) {
                for (MovieModel2 model : movieModels) {
                    // get data
                    recycleViewAdapter2.setMovie(movieModels);
                }
            }
        });
    }



    @Override
    public void onMovieClick2(int pos) {
        // here is going to detail movie that has clicked
        Intent intent = new Intent(getContext(),detailfilm2.class);
        intent.putExtra("movie",recycleViewAdapter2.getSelectedMovie(pos));
        startActivity(intent);
    }
}
