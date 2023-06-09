package com.example.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;


public class fragment2Fragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Users> user_list;
    private static final String SP_KEY="user_list_sp";
    private SharedPreferences sharedPreferences;
    private static final int REQUEST_CODE = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        user_list=new ArrayList<>();

        ////ubah visibility dari texview pemberitahuan yang ada di mainactivity
        recyclerView=view.findViewById(R.id.Favoriteslistresy);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        String json_String=sharedPreferences.getString(SP_KEY,null);
        Gson gson=new Gson();
        TypeToken typeToken=new TypeToken<ArrayList<Users>>(){};
        ArrayList<Users>user_list_local=gson.fromJson(json_String,typeToken.getType());
        //kondisinya

        // Cek apakah RecyclerView memuat data
        int visibility;
        if (user_list_local != null && !user_list_local.isEmpty()) {
            visibility = View.GONE; // Sembunyikan  pemberitahuan jika ada data
        } else {
            visibility = View.VISIBLE;
            // Tampilkan  pemberitahuan jika tidak ada data
        }
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.updateTextViewVisibility(visibility);
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new UserViewAdapter(getContext(),user_list_local));

        return view;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView release_date;
        public ImageView Poster_path,jenisgambar;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            release_date=itemView.findViewById(R.id.release_date);
            Poster_path=itemView.findViewById(R.id.Poster_path);
            jenisgambar=itemView.findViewById(R.id.jenisgambar);
        }
    }
    class UserViewAdapter extends RecyclerView.Adapter{
        private ArrayList<Users>user_list;

        private Context context;
        public UserViewAdapter(Context context,ArrayList<Users>user_list){
            this.user_list=user_list;
            this.context=context;
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
            View view=layoutInflater.inflate(R.layout.fragment_list3,viewGroup,false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            final  Users u=user_list.get(i);
            final UserViewHolder userViewHolder= (UserViewHolder) viewHolder;
            Glide.with(getContext()).load(u.getPoster_path()).into(((UserViewHolder) viewHolder).Poster_path);
            userViewHolder.jenisgambar.setImageURI(Uri.parse(u.getJenisgambar()));
            userViewHolder.title.setText(u.getTitle());
            userViewHolder.release_date.setText(u.getRelease_date());
            ////kirim data ke activity detail film offline
            userViewHolder.itemView.setOnClickListener(view ->{
                Bundle bundle = new Bundle();
                bundle.putString("getJenisgambar",u.getJenisgambar());
                bundle.putString("getTitle",u.getTitle());
                bundle.putString("getPoster_path",u.getPoster_path());
                bundle.putString("getRelease_date",u.getRelease_date());
                bundle.putString("getOverview",u.getOverview());
                bundle.putString("getBackdrop_path",u.getBackdrop_path());
                bundle.putString("getVote_average",u.getVote_average());
                Intent intent = new Intent(getContext(),detailfilmoffline.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,REQUEST_CODE);
                requireActivity().finish();
            } );
        }

        @Override
        public int getItemCount() {
            if (user_list != null) {
                return user_list.size();
            } else {
                return 0;
            }
        }

    }

}
