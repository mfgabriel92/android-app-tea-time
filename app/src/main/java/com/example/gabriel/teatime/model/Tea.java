package com.example.gabriel.teatime.model;

public class Tea {
    private String mName;
    private int mImageResource;

    public Tea(String name, int imageResource) {
        this.mName = name;
        this.mImageResource = imageResource;
    }

    public String getName() {
        return mName;
    }

    public int getImageResource() {
        return mImageResource;
    }
}
