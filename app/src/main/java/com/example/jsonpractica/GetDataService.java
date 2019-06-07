package com.example.jsonpractica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("example/practica.json")
    Call<List<User>> getUsers();
}
