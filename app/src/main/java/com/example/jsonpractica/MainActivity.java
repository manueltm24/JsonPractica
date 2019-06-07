package com.example.jsonpractica;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jsonpractica.Adapters.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context contextApp = this.getApplication();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vinrosa.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataService service =retrofit.create(GetDataService.class);
        service.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                RecyclerView rv = findViewById(R.id.recycler_view);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                rv.setAdapter(new RecycleViewAdapter(getApplicationContext(),response.body()));
                Log.d(TAG, String.valueOf(response.body().size()));
                for(User user: response.body()){
                    userList.add(user);
                    Log.d(TAG,user.getImage_url());
                    Log.d(TAG,user.getName());
                }
                rv.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), rv ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                Log.d(TAG, String.valueOf(position));
                                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG,"ERROR",t);
            }
        });


    }
}
