package com.cvalera.doggos.model;

public class DogVo {
    private String url;
    private String id;

    public DogVo() {

    }

    public DogVo(String url, String id) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
