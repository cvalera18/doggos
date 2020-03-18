package com.cvalera.doggos.Interface;

import com.cvalera.doggos.model.Dogs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TheDogApi {

    @GET("v1/images/search")
    Call<List<Dogs>> getDogs();
}
