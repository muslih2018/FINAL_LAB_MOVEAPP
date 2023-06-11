package com.example.fragment;

import android.annotation.SuppressLint;
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
import com.example.fragment.ADPATERNYA.MovieRecycleView2;
import com.example.fragment.ADPATERNYA.OnMovieListener2;
import com.example.fragment.MODELNYA.MovieModel2;
import com.example.fragment.MODELNYA.viewmodels.PopularMovieListViewModel2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class fragmentprofil extends Fragment implements OnMovieListener2 {

    private PopularMovieListViewModel2 popularMovieListViewModel2;
    private RecyclerView recyclerView2;
    private MovieRecycleView2 recycleViewAdapter2;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentprofil, container, false);
        recyclerView2 = view.findViewById(R.id.recyclerview2);

        popularMovieListViewModel2 = new ViewModelProvider(this).get(PopularMovieListViewModel2.class);
        // configuring recycleview
        configureRecycleView();
        //thread
        progressBar=view.findViewById(R.id.progressBar2);
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
                        if (percentage == 100 && popularMovieListViewModel2!=null) {
                            progressBar.setVisibility(View.GONE);
                            // show popular movies
                            /////pengecekan konesksi
                            if (!isNetworkAvailable()) {
                                showNoConnectionToast();
                            }
                            // data observer
                            ObservasingAnyChangesPopularMovie();
                            popularMovieListViewModel2.getPopularMovie2(1);

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
    private void configureRecycleView() {
        recycleViewAdapter2 = new MovieRecycleView2(this);
        recyclerView2.setAdapter(recycleViewAdapter2);
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void ObservasingAnyChangesPopularMovie() {
        popularMovieListViewModel2.getPopularMovie2().observe(getViewLifecycleOwner(), movieModels -> {
            //observasi ketik data di ganti
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
        // ketika data movie di klik
        Intent intent = new Intent(getContext(),detailfilm2.class);
        intent.putExtra("movie",recycleViewAdapter2.getSelectedMovie(pos));
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
