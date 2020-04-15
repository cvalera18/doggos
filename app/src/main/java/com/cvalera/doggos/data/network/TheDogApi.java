package com.cvalera.doggos.data.network;

import com.cvalera.doggos.model.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TheDogApi {

    @GET("v1/images/search?limit=99&page=0&order=desc")
    Call<List<Dog>> getDogs();
}
