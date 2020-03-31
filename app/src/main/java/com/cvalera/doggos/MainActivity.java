package com.cvalera.doggos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.cvalera.doggos.data.Repository;
import com.cvalera.doggos.model.Dog;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageV = findViewById(R.id.iv1);

        repository = new Repository();

        Button boton = findViewById(R.id.botonG);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                repository.getDogs(new Repository.DogsListener() {

                    @Override
                    public void onSuccess(List<Dog> dogs) {

//                        for (int i = 0; i < dogs.size(); i++) {
//                            Dog dog = dogs.get(i);
//                            String content = dog.getUrl();
//                        }

                        for (Dog dog : dogs) {
                            String content = dog.getUrl();
                            String idcontent = dog.getId();
                            Log.d("Direccion", content);
                            Log.d("ID:", idcontent);
                            Glide.with(getApplicationContext()).load(content).into(imageV);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull String message, @Nullable Throwable throwable) {
                        Log.e("Failure", message);
                    }
                });
            }
        });
    }
//Final
}
