package com.example.t3_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.t3_01.Adapter.AnimeAdapter;
import com.example.t3_01.Entities.Animes;
import com.example.t3_01.services.servicesWeb;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public RecyclerView rv;
    List<Animes> animes= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicesWeb services = retrofit.create(servicesWeb.class);
        Call<List<Animes>> call=services.getContacts();

       call.enqueue(new Callback<List<Animes>>() {
           @Override
           public void onResponse(Call<List<Animes>> call, Response<List<Animes>> response) {
               if (!response.isSuccessful()){
                   Log.e("asd1234", "error");
               }else {

                   Log.i("asdasd12312", new Gson().toJson(response.body()));
                   Log.i("asd32", "Respuesta correcta");

                   animes=response.body();

                   AnimeAdapter adapter=new AnimeAdapter(animes);


                   rv= findViewById(R.id.rvAnimes);
                   rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                   rv.setHasFixedSize(true);
                   rv.setAdapter(adapter);
               }
           }

           @Override
           public void onFailure(Call<List<Animes>> call, Throwable t) {
               Log.e("asd1234", "no hay conexion");
           }
       });
    }
}