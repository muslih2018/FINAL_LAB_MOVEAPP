package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class detailfilmoffline extends AppCompatActivity {
    private ImageView poster,latarposter,back,jenisfilm;
    private TextView title,releaseDate,rating,overview;
    Button tambahkefavorite;
    private ArrayList<Users>user_list;
    boolean isDuplicate = false;
    private static final String SP_KEY="user_list_sp";
    private  SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfilmoffline);
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        String jsonString = sharedPreferences.getString(SP_KEY, null);
        if (jsonString != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Users>>() {}.getType();
            user_list = gson.fromJson(jsonString, type);
        } else {
            user_list = new ArrayList<>();
        }
        tambahkefavorite=findViewById(R.id.tambahkefavorite2);
        title = findViewById(R.id.titleDetail);
        overview = findViewById(R.id.overview);
        releaseDate = findViewById(R.id.releaseDateDetail);
        rating = findViewById(R.id.ratingTextDetail);
        poster = findViewById(R.id.poster);
        latarposter=findViewById(R.id.latarposter);
        jenisfilm = findViewById(R.id.jenisfilm);
        back=findViewById(R.id.back);

        //ambil data yang dikirm dari Favoritefragment

        Bundle bundle = getIntent().getExtras();
        String getJenisgambar = bundle.getString("getJenisgambar");
        String getTitle = bundle.getString("getTitle");
        String getPoster_path = bundle.getString("getPoster_path");
        String getRelease_date = bundle.getString("getRelease_date");
        String getOverview = bundle.getString("getOverview");
        String getBackdrop_path = bundle.getString("getBackdrop_path");
        String getVote_average = bundle.getString("getVote_average");

        //tampilkan data yang di ambil
        title.setText(getTitle);
        overview.setText(getOverview);
        releaseDate.setText(getRelease_date);
        rating.setText(getVote_average);
        Glide.with(this).load(getPoster_path).into(poster);
        Glide.with(this).load(getBackdrop_path).into(latarposter);
        jenisfilm.setImageURI(Uri.parse(getJenisgambar));

        getSupportActionBar().setTitle("Movies Detail");
        //kembali
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailfilmoffline.this, MainActivity.class);
                boolean datacek = true ;
                intent.putExtra("data_key", datacek);
                startActivity(intent);
                finish();
            }
        });
        ///kirim data ke fragment favorite(Mainactivity) ):
       // Buat objek Users baru dengan data yang dibutuh
        Users newUser = new Users(getTitle,getPoster_path,getBackdrop_path,getOverview,getRelease_date,getVote_average,getJenisgambar);
//        // Periksa apakah data sudah ada dalam user_list atau tdk
//        ///ambil  color dari color resource
        for (Users user : user_list) {
            if (user.getTitle().equalsIgnoreCase(newUser.getTitle())) {
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

//        ////tambah ke favvorite ketika di klik
        tambahkefavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users duplicateUser = null;
                String moviename = null;
                String moviename2 = null;
                for (Users user : user_list) {
                    moviename= user.getTitle();
                    if (user.getTitle().equalsIgnoreCase(newUser.getTitle())) {
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
//                // Jika data belum ada dalam user_list, tambahkan ke dalamnya
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