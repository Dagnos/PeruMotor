package com.example.prueba.Views;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.prueba.Adapter.OrdenTrabajoAdapter;
import com.example.prueba.Interface.APIPerumotor;
import com.example.prueba.Models.OrdenTrabajo;
import com.example.prueba.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ArrayList<OrdenTrabajo> ordenTrabajos = new ArrayList<>();
    private OrdenTrabajoAdapter ordenTrabajoAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.ot_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getOrdenTrabajo();

        //boton flotante... agregar Orden de Trabajo.

        FloatingActionButton fab = findViewById(R.id.fab_btn);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FormularioOTActivity.class);
                startActivity(i);
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab_btn2);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BuscarOTActivity.class);
                startActivity(i);
            }
        });

    }

    private void getOrdenTrabajo(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor=retrofit.create(APIPerumotor.class);
        Call<List<OrdenTrabajo>> call=apiPerumotor.getOrdenTrabajo();

        call.enqueue(new Callback<List<OrdenTrabajo>>() {
            @Override
            public void onResponse(Call<List<OrdenTrabajo>> call, Response<List<OrdenTrabajo>> response) {

                ordenTrabajos = new  ArrayList<>(response.body());
                ordenTrabajoAdapter= new OrdenTrabajoAdapter(MainActivity.this,ordenTrabajos);
                recyclerView.setAdapter(ordenTrabajoAdapter);
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<OrdenTrabajo>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
