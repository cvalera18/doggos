package com.cvalera.doggos.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cvalera.doggos.data.network.TheDogApi;
import com.cvalera.doggos.model.Dog;
import com.cvalera.doggos.model.DogVo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private TheDogApi theDogApi;

    public Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thedogapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.theDogApi = retrofit.create(TheDogApi.class);
    }

    public void getDogs(DogsListener listener) {

        Call<List<Dog>> call = theDogApi.getDogs();
        call.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                if (!response.isSuccessful()) {
                    Log.d("Respuesta", "Codigo: " + response.code());
                    listener.onFailure("Codigo: " + response.code(), null);
                    return;
                }
                List<Dog> dogList = response.body();

                if (dogList != null) {
                    ArrayList<DogVo> dogVoList = new ArrayList<>();
                    for (Dog dog : dogList) {
                        dogVoList.add(new DogVo(dog.getUrl(), dog.getId()));
                    }
                    listener.onSuccess(dogVoList);
                }
                else {
                    listener.onFailure("Body is null", null);
                }
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                listener.onFailure(t.getLocalizedMessage(), t);

            }
        });
    }

    public interface DogsListener {
        void onSuccess(List<DogVo> dogs);

        void onFailure(@NonNull String message, @Nullable Throwable throwable);
    }
}
