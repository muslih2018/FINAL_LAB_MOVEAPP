package com.example.fragment;

import java.util.ArrayList;

public class Users {
    private static ArrayList<Users> user_list=new ArrayList<>();
    private String title;
    private String poster_path;
    private String backdrop_path;

    private String release_date;
    private String vote_average;
    private String overview;
    private  String jenisgambar;

    public Users(){

    }

    public Users(String title, String poster_path, String backdrop_path,String overview,String release_date,String vote_average,String jenisgambar) {
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.jenisgambar = jenisgambar;



    }

    public static ArrayList<Users> getUser_list() {
        return user_list;
    }

    public static void setUserArrayList(Users p) {
        Users.user_list.add(p);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getJenisgambar() {
        return jenisgambar;
    }

    public void setJenisgambar(String jenisgambar) {
        this.jenisgambar = jenisgambar;
    }


}
