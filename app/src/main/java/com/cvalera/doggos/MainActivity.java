package com.cvalera.doggos;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cvalera.doggos.data.Repository;
import com.cvalera.doggos.model.AdaptadorDogs;
import com.cvalera.doggos.model.DogVo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerDogs;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new Repository();
        recyclerDogs = findViewById(R.id.rv_dogs);
        recyclerDogs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        AdaptadorDogs adapter = new AdaptadorDogs(new ArrayList<>());
        recyclerDogs.setAdapter(adapter);

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
