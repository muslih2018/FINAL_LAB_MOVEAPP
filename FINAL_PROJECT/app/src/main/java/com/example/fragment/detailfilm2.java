package com.example.fragment;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fragment.MODELNYA.MovieModel;
import com.example.fragment.MODELNYA.MovieModel2;
import com.example.fragment.UTILIS.Credentials;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class detailfilm2 extends AppCompatActivity {
    private ImageView poster,latarposter,back;
    private TextView title,releaseDate,rating,overview;
    Button tambahkefavorite;
    private ArrayList<Users> user_list;
    boolean isDuplicate = false;
    private static final String SP_KEY="user_list_sp";
    private SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfilm2);
        tambahkefavorite=findViewById(R.id.tambahkefavorite2);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String jsonString = sharedPreferences.getString(SP_KEY, null);
        if (jsonString != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Users>>() {}.getType();
            user_list = gson.fromJson(jsonString, type);
        } else {
            user_list = new ArrayList<>();
        }

        title = findViewById(R.id.titleDetail);
        overview = findViewById(R.id.overview);
        releaseDate = findViewById(R.id.releaseDateDetail);
        rating = findViewById(R.id.ratingTextDetail);
        poster = findViewById(R.id.poster);
        latarposter=findViewById(R.id.latarposter);
        back=findViewById(R.id.back);

        MovieModel2 model = getIntent().getParcelableExtra("movie");
        // string text
        String getRating = String.valueOf(model.getVote_average());
        title.setText(model.getName());
        releaseDate.setText(model.getFirst_air_date());
        overview.setText(model.getOverview());
        rating.setText(getRating);
        // poster
        Glide.with(this).load(Credentials.Poster_URL + model.getPoster_path()).into(poster);
        // latar poster
        Glide.with(this).load(Credentials.Poster_URL + model.getBackdrop_path()).into(latarposter);

        getSupportActionBar().setTitle("Tv Shows Detail");

//        kembali
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ///kirim data ke fragment favorite(Mainactivity) ):
        ////pengambilan data
        String backdropUrl = Credentials.Poster_URL + model.getBackdrop_path();
        Uri posterUri1 = Uri.parse(backdropUrl);
        String backdropUrl2 = Credentials.Poster_URL + model.getPoster_path();
        Uri posterUri21 = Uri.parse(backdropUrl2);
        String posterUri = posterUri1.toString();
        String posterUri2 = posterUri21.toString();
        String title,releaseDate,overview,rating;
        title=model.getName();
        releaseDate=model.getFirst_air_date();
        overview=model.getOverview();
        rating=String.valueOf(model.getVote_average());
        //ambil Resources terus ubah ke string jenis string (jenis dari film)
        Resources res = getResources();
        String jenis = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + res.getResourcePackageName(R.drawable.baseline_tv_24) + '/' + res.getResourceTypeName(R.drawable.baseline_tv_24) + '/' + res.getResourceEntryName(R.drawable.baseline_tv_24);
        // Buat objek Users baru dengan data yang dibutuh
        Users newUser = new Users(title,posterUri,posterUri2,overview,releaseDate,rating,jenis);
        // Periksa apakah data sudah ada dalam user_list atau tdk
        ///ambil  color dari color resource
        for (Users user : user_list) {
            if (user.getTitle().equalsIgnoreCase(newUser.getTitle()) && user.getOverview().equalsIgnoreCase(newUser.getOverview())) {
                tambahkefavorite.setBackgroundResource(R.drawable.buttonshaperemove);
                tambahkefavorite.setText("REMOVE FROM FAVORITE");
                isDuplicate = true;
                break;
            }
            else {
                tambahkefavorite.setBackgroundResource(R.drawable.buttonshape);
                tambahkefavorite.setText("ADD TO FAVORITE");
            }
        }
        ////tambah ke favvorite ketika di klik
        tambahkefavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users duplicateUser = null;
                String moviename = null;
                String moviename2 = null;
                for (Users user : user_list) {
                    moviename= user.getTitle();
                    if (user.getTitle().equalsIgnoreCase(newUser.getTitle()) && user.getOverview().equalsIgnoreCase(newUser.getOverview())) {
                        tambahkefavorite.setBackgroundResource(R.drawable.buttonshaperemove);
                        tambahkefavorite.setText("REMOVE FROM FAVORITE");
                        isDuplicate = true;
                        duplicateUser = user;
                        break;
                    }
                    else {
                        tambahkefavorite.setBackgroundResource(R.drawable.buttonshape);
                        tambahkefavorite.setText("ADD TO FAVORITE");
                    }
                }
                // Jika data belum ada dalam user_list, tambahkan ke dalamnya
                if (!isDuplicate) {
                    user_list.add(newUser);
                    moviename2= newUser.getTitle();
                    // Simpan data baru ke SharedPreferences
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(user_list);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SP_KEY, jsonString);
                    editor.apply();
                    tambahkefavorite.setBackgroundResource(R.drawable.buttonshaperemove);
                    tambahkefavorite.setText("REMOVE FROM FAVORITE");
                    Toast.makeText(getApplicationContext(), moviename2+" added to favorite successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), moviename+" removed to favorite successfully", Toast.LENGTH_SHORT).show();
                    user_list.remove(duplicateUser);
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(user_list);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SP_KEY, jsonString);
                    editor.apply();
                    isDuplicate = false;
                    tambahkefavorite.setBackgroundResource(R.drawable.buttonshape);
                    tambahkefavorite.setText("ADD TO FAVORITE");

                }
            }
        });


    }
}
