package com.example.t3_01.services;

import com.example.t3_01.Entities.Animes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface servicesWeb {
    @GET("Animes/")
    Call<List<Animes>> getContacts();

//    @GET("contacts/{id}")
//    Call<Contacts> findContact(@Path("id") int id);
}
