package com.cvalera.doggos;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cvalera.doggos.data.Repository;
import com.cvalera.doggos.model.AdaptadorDogs;
import com.cvalera.doggos.model.Dog;
import com.cvalera.doggos.model.DogVo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<DogVo> listaDogs;
    RecyclerView recyclerDogs;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDogs = new ArrayList<>();
        recyclerDogs = findViewById(R.id.rv1);
        recyclerDogs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        AdaptadorDogs adapter = new AdaptadorDogs(listaDogs);
        recyclerDogs.setAdapter(adapter);

        repository = new Repository();
        repository.getDogs(new Repository.DogsListener() {

            @Override
            public void onSuccess(List<DogVo> dogs) {

                adapter.setListaDogs(dogs);
            }

            @Override
            public void onFailure(@NonNull String message, @Nullable Throwable throwable) {
                Log.e("Failure", message);
            }
        });
    }

//Final
}
