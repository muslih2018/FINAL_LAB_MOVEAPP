package com.example.fragment.REQUEST;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.MovieModel2;
import com.example.fragment.RESPONSE.PopularMovieResponses;
import com.example.fragment.RESPONSE.PopularMovieResponses2;
import com.example.fragment.UTILIS.AppExecutor;
import com.example.fragment.UTILIS.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class PopularMovieApiClient2 {
    private static PopularMovieApiClient2 instance;

    public static PopularMovieApiClient2 getInstance() {
        if (instance == null) {
            instance = new PopularMovieApiClient2();
        }
        return instance;
    }

    // live data
    private final MutableLiveData<List<MovieModel2>> popularMovieLiveData;

    // global variable for runnable
    private PopularRunnable popularRunnable;

    private PopularMovieApiClient2() {
        popularMovieLiveData = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel2>> getPopularMovie2() {
        return popularMovieLiveData;
    }

    public void getPopularMovie2(int page) {
        if (popularRunnable != null) {
            popularRunnable = null;
        }

        popularRunnable = new PopularRunnable(page);

        final Future handler = AppExecutor.getInstance().getNetworkIO().submit(popularRunnable);

        AppExecutor.getInstance().getNetworkIO().schedule(() -> {
            // canceling retrofit call
            handler.cancel(true);
        }, 3000, TimeUnit.MILLISECONDS);
    }

    // retrieve data from rest api using runnable
    private class PopularRunnable implements Runnable {

        private final int page;
        boolean cancleRequest;

        public PopularRunnable(int page) {
            this.page = page;
            cancleRequest = false;
        }

        @Override
        public void run() {
            // getting request
            try {
                Response response = getPopularMovie2(page).execute();

                if (cancleRequest) {
                    return;
                }

                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        assert response.body() != null;
                        List<MovieModel2> popularMovieList = new ArrayList<>(((PopularMovieResponses2) response.body()).getMovies());

                        if (page == 1) {
                            // send data to live data
                            // post value -> using banckground thread
                            // set value
                            popularMovieLiveData.postValue(popularMovieList);
                        } else {
                            List<MovieModel2> currentMovie = popularMovieLiveData.getValue();
                            assert currentMovie != null;
                            currentMovie.addAll(popularMovieList);
                            popularMovieLiveData.postValue(currentMovie);
                        }
                    } else {
                        assert response.errorBody() != null;
                        System.out.println(response.errorBody().string());
                        popularMovieLiveData.postValue(null);
                    }
                } else {
                    System.out.println("request isnt successful");
                }
            } catch (IOException e) {
                System.out.println(e);
                popularMovieLiveData.postValue(null);
            }
        }

        // method getPopularMovie
        private Call<PopularMovieResponses2> getPopularMovie2(int page) {
            return ApiService2.getMovieApi().getPopularMovie2(Credentials.KEY, page);
        }
    }
}