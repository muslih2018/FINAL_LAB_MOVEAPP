package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fragment.ADPATERNYA.MovieRecycleView;
import com.example.fragment.ADPATERNYA.OnMovieListener;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.viewmodels.PopularMovieListViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FirstFragment extends Fragment implements OnMovieListener {

    private PopularMovieListViewModel popularMovieListViewModel;
    private RecyclerView recyclerView;
    private MovieRecycleView recycleViewAdapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        // show popular movies
        popularMovieListViewModel = new ViewModelProvider(this).get(PopularMovieListViewModel.class);

        // configuring recycleview
        configureRecycleView();
        //thread
        progressBar=view.findViewById(R.id.progressBar);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            try {
//simulate process in background thread
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(300);
                    int percentage = i * 10;
                    handler.post(() -> {
//update ui in main thread
                        if (percentage == 100 && popularMovieListViewModel!=null) {
                            progressBar.setVisibility(View.GONE);
                            /////pengecekan konesksi
                            if (!isNetworkAvailable()) {
                                showNoConnectionToast();
                            }
                            // data observer
                            ObservasingAnyChangesPopularMovie();
                            popularMovieListViewModel.getPopularMovie(1);
                        } else {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return view;
    }
    // init recycleView dan tambah data
    private void configureRecycleView() {
        recycleViewAdapter = new MovieRecycleView(this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void ObservasingAnyChangesPopularMovie() {
        popularMovieListViewModel.getPopularMovie().observe(getViewLifecycleOwner(), movieModels -> {
            // observasi ketik data di ganti
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
        // ketika data movie di klik
        Intent intent = new Intent(getContext(),detailfilm.class);
        intent.putExtra("movie",recycleViewAdapter.getSelectedMovie(pos));
        startActivity(intent);
    }

    //cek kondisi jaringan
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void showNoConnectionToast() {
        Toast.makeText(getContext(), "Cek koneksi internet Anda", Toast.LENGTH_SHORT).show();
    }
}