package com.cvalera.doggos.model;

import com.google.gson.annotations.SerializedName;

public class Dog {
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;

    public Dog(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
