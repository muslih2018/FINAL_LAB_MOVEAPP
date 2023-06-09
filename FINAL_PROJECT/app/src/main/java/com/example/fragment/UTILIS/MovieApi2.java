package com.example.fragment.UTILIS;

import com.example.fragment.RESPONSE.PopularMovieResponses;
import com.example.fragment.RESPONSE.PopularMovieResponses2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi2 {



    String kondisi2="tv/popular";


    @GET(kondisi2)
    Call<PopularMovieResponses2> getPopularMovie2(@Query("api_key") String key,
                                                 @Query("page") int page);
}
