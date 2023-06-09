package com.example.fragment.REQUEST;

import com.example.fragment.UTILIS.Credentials;
import com.example.fragment.UTILIS.MovieApi;
import com.example.fragment.UTILIS.MovieApi2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService2 {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Credentials.BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();
    private static MovieApi2 movieApi2 = retrofit.create(MovieApi2.class);

    public static MovieApi2 getMovieApi() {
        return movieApi2;
    }
}
