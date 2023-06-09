package com.example.fragment.UTILIS;

import androidx.fragment.app.Fragment;

import com.example.fragment.RESPONSE.PopularMovieResponses;
import com.example.fragment.RESPONSE.PopularMovieResponses2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {


    String kondisi1="movie/popular";
    String kondisi2="tv/popular";

    @GET(kondisi1)
    Call<PopularMovieResponses> getPopularMovie(@Query("api_key") String key,
                                                @Query("page") int page);
    @GET(kondisi2)
    Call<PopularMovieResponses2> getPopularMovie2(@Query("api_key") String key,
                                                 @Query("page") int page);
}
