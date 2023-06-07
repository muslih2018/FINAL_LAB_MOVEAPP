package com.example.fragment;

import android.net.Uri;

public class DataItem {
    private String imageUri;
    private String text;

    public DataItem(Uri imageUri, String text) {
        this.imageUri = String.valueOf(imageUri);
        this.text = text;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getText() {
        return text;
    }
}