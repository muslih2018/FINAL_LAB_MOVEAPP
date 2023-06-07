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

public class fragmentprofil extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<DataItem> dataList2;
    Uri imageUri;
    LinearLayout toprofil2;
    TextView suggestion;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentprofil, container, false);
        recyclerView = view.findViewById(R.id.tvData);
        dataList2 = new ArrayList<>();
        // Set  adapter
        adapter = new MyAdapter(dataList2);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        recyclerView.setAdapter(adapter);
        //ke profil
        toprofil2 = view.findViewById(R.id.to_Profil);
        //kondisi hilangkan text suggestion
        Resources res = getResources();
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + res.getResourcePackageName(R.drawable.titans) + '/' + res.getResourceTypeName(R.drawable.titans) + '/' + res.getResourceEntryName(R.drawable.titans);
        String uri2 = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + res.getResourcePackageName(R.drawable.logingais) + '/' + res.getResourceTypeName(R.drawable.logingais) + '/' + res.getResourceEntryName(R.drawable.logingais);
        Uri imageUri4 = Uri.parse(uri);
        Uri imageUri5 = Uri.parse(uri2);

        dataList2.add(new DataItem(imageUri5,"MUSUH TERLIHAT"));
        dataList2.add(new DataItem(imageUri4,"KERJA BAGUS"));
        dataList2.add(new DataItem(imageUri4,"AKU PUNYA INI"));
        dataList2.add(new DataItem(imageUri5,"THE LOGIN "));
        dataList2.add(new DataItem(imageUri5,"MUSUH TERLIHAT"));
        dataList2.add(new DataItem(imageUri4,"KERJA BAGUS"));
        dataList2.add(new DataItem(imageUri4,"AKU PUNYA INI"));
        dataList2.add(new DataItem(imageUri5,"THE LOGIN "));


        return view;
    }
    // Method untuk mendapatkan dataList dari dalam fragment
    public List<DataItem> getDataList2() {
        return dataList2;
    }




    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<DataItem> dataList2;

        public MyAdapter( List<DataItem> dataList2) {
            this.dataList2 = dataList2;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list2, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            DataItem dataItem = dataList2.get(position);
            holder.imageView.setImageURI(Uri.parse(dataItem.getImageUri()));
            holder.textView.setText(dataItem.getText());

        }

        @Override
        public int getItemCount() {
            return dataList2.size();
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
