package com.example.fragment;

        import android.annotation.SuppressLint;
        import android.content.ContentResolver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.net.Uri;
        import android.os.Bundle;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

public class fragment2Fragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<DataItem> dataList3;
    Uri imageUri;
    LinearLayout toprofil2;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        recyclerView = view.findViewById(R.id.tvData);
        dataList3 = new ArrayList<>();
        // Set  adapter
        adapter = new MyAdapter(dataList3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //ke profil
        toprofil2 = view.findViewById(R.id.to_Profil);
        //kondisi hilangkan text suggestion
        Resources res = getResources();
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + res.getResourcePackageName(R.drawable.titans) + '/' + res.getResourceTypeName(R.drawable.titans) + '/' + res.getResourceEntryName(R.drawable.titans);
        String uri2 = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + res.getResourcePackageName(R.drawable.logingais) + '/' + res.getResourceTypeName(R.drawable.logingais) + '/' + res.getResourceEntryName(R.drawable.logingais);
        Uri imageUri4 = Uri.parse(uri);
        Uri imageUri5 = Uri.parse(uri2);
        dataList3.add(new DataItem(imageUri5,"MUSUH TERLIHAT2"));
        dataList3.add(new DataItem(imageUri4,"KERJA BAGUS"));
        dataList3.add(new DataItem(imageUri4,"AKU PUNYAl INI"));
        dataList3.add(new DataItem(imageUri5,"THE LOGIkN "));
        dataList3.add(new DataItem(imageUri4,"AKU PUNYA INlI"));
        dataList3.add(new DataItem(imageUri5,"THE LOG,IN "));
        dataList3.add(new DataItem(imageUri4,"AKU PUNpYA INI"));
        dataList3.add(new DataItem(imageUri5,"THE LOGpIN "));


        return view;
    }
    // Method untuk mendapatkan dataList dari dalam fragment
    public List<DataItem> getDataList3() {
        return dataList3;
    }




    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<DataItem> dataList3;

        public MyAdapter( List<DataItem> dataList3) {
            this.dataList3 = dataList3;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list3, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            DataItem dataItem = dataList3.get(position);
            holder.imageView.setImageURI(Uri.parse(dataItem.getImageUri()));
            holder.textView.setText(dataItem.getText());

        }

        @Override
        public int getItemCount() {
            return dataList3.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;


            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.gambar);
                textView = itemView.findViewById(R.id.desc);
            }

        }



    }

}
