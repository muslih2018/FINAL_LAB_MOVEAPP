package com.example.fragment.MODELNYA;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel2 implements Parcelable {
    private int id;
    private String name;
    private String poster_path;
    private String backdrop_path;

    private String first_air_date;
    private float vote_average;
    private String overview;
    private String original_language;

    public MovieModel2(int id, String name, String poster_path, String backdrop_path, String first_air_date, float vote_average, String overview, String original_language) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.first_air_date = first_air_date;
        this.vote_average = vote_average;
        this.overview = overview;
        this.original_language = original_language;
    }

    protected MovieModel2(Parcel in) {
        id = in.readInt();
        name = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        first_air_date = in.readString();
        vote_average = in.readFloat();
        overview = in.readString();
        original_language = in.readString();
    }

    public static final Creator<MovieModel2> CREATOR = new Creator<MovieModel2>() {
        @Override
        public MovieModel2 createFromParcel(Parcel in) {
            return new MovieModel2(in);
        }

        @Override
        public MovieModel2[] newArray(int size) {
            return new MovieModel2[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(first_air_date);
        parcel.writeFloat(vote_average);
        parcel.writeString(overview);
        parcel.writeString(original_language);
    }

    @Override
    public String toString() {
        return "MovieModel2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", first_air_date='" + first_air_date + '\'' +
                ", vote_average=" + vote_average +
                ", overview='" + overview + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}
