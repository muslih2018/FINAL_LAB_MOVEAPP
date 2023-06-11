package com.example.fragment;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton  btnPost;
    CardView TV_shows,Favorites,Movies;
    ImageView btnHome,btnProfil,panah,cari;
    FirstFragment FirstFragment;
    fragment2Fragment fragment2Fragment;

    fragmentprofil fragmentprofil;
    TextView navbar,hot;
    View garisbawah,garisbawah2;
    TextView textView,pemberitahuanoffline;
    LinearLayout tempatcategory,category,caritampil,container;
    RelativeLayout paddingakalin;
    boolean datacek;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hapus title barnya
        getSupportActionBar().hide();

        pemberitahuanoffline=findViewById(R.id.pemberitahuanoffline);
        container=findViewById(R.id.container);
        textView = findViewById(R.id.pemberitahuan);
        TV_shows=findViewById(R.id.TV_shows);
        hot=findViewById(R.id.hot);
        Movies=findViewById(R.id.movies);
        garisbawah=findViewById(R.id.garisbawah);
        garisbawah2=findViewById(R.id.garisbawah2);
        navbar=findViewById(R.id.navbar);
        Favorites=findViewById(R.id.Favorites);
        btnHome = findViewById(R.id.home);
        btnPost = findViewById(R.id.post);
        btnProfil = findViewById(R.id.profil);
        btnHome.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnProfil.setOnClickListener(this);
        Favorites.setOnClickListener(this);
        TV_shows.setOnClickListener(this);
        Movies.setOnClickListener(this);
        navbar.setText("Movies");

        /////cek kondisi pada sudah kembali dari detailoffline atau tdk );
        Intent intent = getIntent();
        if(intent!=null){
        datacek =intent.getBooleanExtra("data_key",false);
        if(datacek==false){
        menuHome();
        }
        else {
            hot.setText("Your Favorites");
            navbar.setText("Favorites");
            garisbawah2.setVisibility(View.GONE);
            garisbawah.setVisibility(View.GONE);
            pemberitahuanoffline.setVisibility(View.GONE);
            container.setVisibility(View.VISIBLE);
            datacek=false;
            menuPost();
        }
        }


        ////untuk menu cari
        cari=findViewById(R.id.cari);
        caritampil=findViewById(R.id.caritampil);
        paddingakalin=findViewById(R.id.paddingakalin);
        final boolean[] isHidden2 = {true};
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(caritampil, new AutoTransition());

                if (isHidden2[0]) {
                    cari.setImageResource(R.drawable.baseline_close_24);
                    paddingakalin.setPadding(0,45,0,0);
                    caritampil.setVisibility(View.VISIBLE);
                    isHidden2[0] = false;
                } else {
                    cari.setImageResource(R.drawable.ic_search_light_gray_24dp);
                    paddingakalin.setPadding(0,0,0,0);
                    caritampil.setVisibility(View.GONE);
                    isHidden2[0] = true;
                }
            }
        });
        ////untuk menu category
        category=findViewById(R.id.category);
        tempatcategory=findViewById(R.id.tempatcategory);
        panah=findViewById(R.id.panah);
        ///sedikit animasi );
        final boolean[] isHidden = {true};
        final boolean[] isRotated = {false};

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(tempatcategory, new AutoTransition());

                if (isHidden[0] && !isRotated[0]) {
                    Animation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(300);
                    rotateAnimation.setFillAfter(true);
                    panah.startAnimation(rotateAnimation);
                    isRotated[0] = true;
                    tempatcategory.setVisibility(View.VISIBLE);
                    isHidden[0] = false;
                } else {
                    Animation rotateAnimation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(300);
                    rotateAnimation.setFillAfter(true);
                    panah.startAnimation(rotateAnimation);
                    isRotated[0] = false;
                    tempatcategory.setVisibility(View.GONE);
                    isHidden[0] = true;
                }
            }
        });
    }
   ////metode untuk mengubah visbility dari text belum ada data di favorite
   public void updateTextViewVisibility(int visibility) {
       textView.setVisibility(visibility);
   }
    //////semua fragmen
    void menuHome() {

//        ubah warna layout button
        Movies.setBackgroundTintList(getResources().getColorStateList(R.color.button));
        Favorites.setBackgroundTintList(getResources().getColorStateList(R.color.element));
        TV_shows.setBackgroundTintList(getResources().getColorStateList(R.color.element));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("FirstFragment");
        Fragment fragment2 = fragmentManager.findFragmentByTag("fragmenprofil");
        Fragment fragment3 = fragmentManager.findFragmentByTag("fragmenpost");

        // Sembunyikan Fragment sebelumnya (jika ada)

        if(fragment != null){
            if(fragment3 != null && fragment2 != null && fragment3.isVisible()&& fragment2.isVisible()){
                fragmentTransaction.hide(fragment3);
                fragmentTransaction.hide(fragment2);
            }
            if(fragment3 != null && fragment3.isVisible() ){
                fragmentTransaction.hide(fragment3);
            }
            if(fragment2 != null && fragment2.isVisible() ){
                fragmentTransaction.hide(fragment2);
            }
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }

        else {
            FirstFragment = new FirstFragment();
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,FirstFragment, "FirstFragment");
            if(fragment3 != null && fragment2 != null && fragment2.isVisible() && fragment3.isVisible()){
                fragmentTransaction.hide(fragment3);
                fragmentTransaction.hide(fragment2);

            }  if (fragment3 != null && fragment3.isVisible()) {
                fragmentTransaction.hide(fragment3);

            }
            if (fragment2 != null && fragment2.isVisible()) {
                fragmentTransaction.hide(fragment2);


            }
            /////pengecekan konesksi
            if (!isNetworkAvailable()) {
                showNoConnectionToast();
            }
            else {
                pemberitahuanoffline.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
            }
            fragmentTransaction.commit();
            ft.commit();

        }
    }
    void menuPost() {
//        ubah warna layout button
        //        ubah warna layout button
        Movies.setBackgroundTintList(getResources().getColorStateList(R.color.element));
        Favorites.setBackgroundTintList(getResources().getColorStateList(R.color.button));
        TV_shows.setBackgroundTintList(getResources().getColorStateList(R.color.element));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("FirstFragment");
        Fragment fragment2 = fragmentManager.findFragmentByTag("fragmenprofil");
        Fragment fragment3 = fragmentManager.findFragmentByTag("fragmenpost");

        // Sembunyikan Fragment sebelumnya (jika ada)

        if(fragment3 != null){
            fragmentTransaction.remove(fragment3);
            fragment2Fragment = new fragment2Fragment();
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,fragment2Fragment, "fragmenpost");
            if(fragment != null && fragment2 != null && fragment.isVisible()&& fragment2.isVisible()){
                fragmentTransaction.hide(fragment);
                fragmentTransaction.hide(fragment2);

            }  if (fragment != null&& fragment.isVisible()) {
                fragmentTransaction.hide(fragment);


            }
            if (fragment2 != null&& fragment2.isVisible()) {
                fragmentTransaction.hide(fragment2);


            }
            fragmentTransaction.commit();
            ft.commit();
        }

        else {
            fragment2Fragment = new fragment2Fragment();
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,fragment2Fragment, "fragmenpost");
            if(fragment != null && fragment2 != null && fragment.isVisible()&& fragment2.isVisible()){
                fragmentTransaction.hide(fragment);
                fragmentTransaction.hide(fragment2);

            }  if (fragment != null&& fragment.isVisible()) {
                fragmentTransaction.hide(fragment);


            }
            if (fragment2 != null&& fragment2.isVisible()) {
                fragmentTransaction.hide(fragment2);


            }
            fragmentTransaction.commit();
            ft.commit();

        }
    }
    void menuProfil() {
//        ubah warna layout button
        Movies.setBackgroundTintList(getResources().getColorStateList(R.color.element));
        Favorites.setBackgroundTintList(getResources().getColorStateList(R.color.element));
        TV_shows.setBackgroundTintList(getResources().getColorStateList(R.color.button));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("FirstFragment");
        Fragment fragment2 = fragmentManager.findFragmentByTag("fragmenprofil");
        Fragment fragment3 = fragmentManager.findFragmentByTag("fragmenpost");

        // Sembunyikan Fragment sebelumnya (jika ada)

        if(fragment2 != null){
            if(fragment != null && fragment3 != null && fragment.isVisible()&& fragment3.isVisible()){
                fragmentTransaction.hide(fragment);
                fragmentTransaction.hide(fragment3);
                fragmentTransaction.show(fragment2);
                fragmentTransaction.commit();
            }
            else if(fragment != null && fragment.isVisible() ){
                fragmentTransaction.hide(fragment);
                fragmentTransaction.show(fragment2);
                fragmentTransaction.commit();
            }
            else if(fragment3 != null&& fragment3.isVisible() ){
                fragmentTransaction.hide(fragment3);
                fragmentTransaction.show(fragment2);
                fragmentTransaction.commit();
            }
            else {
                fragmentTransaction.show(fragment2);
                fragmentTransaction.commit();
            }

        }

        else {
            fragmentprofil = new fragmentprofil();
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, fragmentprofil, "fragmenprofil");
            if(fragment != null && fragment3 != null && fragment3.isVisible() && fragment.isVisible()){
                fragmentTransaction.hide(fragment);
                fragmentTransaction.hide(fragment3);

            }  if (fragment != null && fragment.isVisible()) {
                fragmentTransaction.hide(fragment);
            }
            if (fragment3 != null && fragment3.isVisible()) {
                fragmentTransaction.hide(fragment3);
            }
            fragmentTransaction.commit();
            ft.commit();
        }

    }


    @Override
    public void onClick(View v) {
        if (v == btnHome || v==Movies) {
            if( navbar.equals("Movies")){
                ///tidak melakukan apa2 karena berada di menu yang sama
            }
            else {
                navbar.setText("Movies");
                garisbawah.setVisibility(View.VISIBLE);
                garisbawah2.setVisibility(View.GONE);
                hot.setText("Hot Movies");
                textView.setVisibility(View.GONE);
                /////pengecekan konesksi
                if (!isNetworkAvailable()) {
                    showNoConnectionToast();
                }
                else {
                    pemberitahuanoffline.setVisibility(View.GONE);
                    container.setVisibility(View.VISIBLE);
                }
                menuHome();}
        }
        if (v == btnPost || v==Favorites) {
            if( navbar.equals("Favorites")){
                ///tidak melakukan apa2 karena berada di menu yang sama
            }
            else {
                hot.setText("Your Favorites");
                navbar.setText("Favorites");
                garisbawah2.setVisibility(View.GONE);
                garisbawah.setVisibility(View.GONE);
                pemberitahuanoffline.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                menuPost();
            }

        }
        if (v == btnProfil || v==TV_shows) {
            if( navbar.equals("TV_Shows")) {
               ///tidak melakukan apa2 karena berada di menu yang sama
            }
            else {
                hot.setText("Hot Tv Shows");
               navbar.setText("TV_Shows");
               garisbawah2.setVisibility(View.VISIBLE);
               textView.setVisibility(View.GONE);
               garisbawah.setVisibility(View.GONE);
                /////pengecekan konesksi
                if (!isNetworkAvailable()) {
                    showNoConnectionToast();
                }
                else {
                    pemberitahuanoffline.setVisibility(View.GONE);
                    container.setVisibility(View.VISIBLE);
                }
               menuProfil();}

        }


    }
    //cek kondisi jaringan
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void showNoConnectionToast() {
         pemberitahuanoffline.setVisibility(View.VISIBLE);
         container.setVisibility(View.GONE);
    }
}
