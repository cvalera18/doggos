package com.cvalera.doggos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cvalera.doggos.Interface.TheDogApi;
import com.cvalera.doggos.model.Dogs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button boton=findViewById(R.id.botonG);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDogs();

            }
        });




    }

    private void getDogs(){

        EditText tvURL = findViewById(R.id.tv1);
        EditText tvID = findViewById(R.id.tv2);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thedogapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheDogApi theDogApi = retrofit.create(TheDogApi.class);
        Call<List<Dogs>> call = theDogApi.getDogs();
        call.enqueue(new Callback<List<Dogs>>() {
            @Override
            public void onResponse(Call<List<Dogs>> call, Response<List<Dogs>> response) {
                if(!response.isSuccessful()){
                    Log.d("Respuesta","Codigo: "+response.code());
                    return;
                }
                List<Dogs> dogsList = response.body();

                for (Dogs dogs: dogsList){
                    String content = dogs.getUrl();
                    String idcontent = dogs.getId();
                    Log.d("Direccion",content);
                    tvURL.setText("Dirección: "+content);
                    tvID.setText("ID: "+idcontent);
                }

            }

            @Override
            public void onFailure(Call<List<Dogs>> call, Throwable t) {
                Log.e("Error",t.getMessage());

            }
        });

    }
//Final
}
