package com.example.prueba.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba.Adapter.OrdenTrabajoAdapter;
import com.example.prueba.Interface.APIPerumotor;
import com.example.prueba.Models.OrdenTrabajo;
import com.example.prueba.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscarOTActivity extends AppCompatActivity {

    ArrayList<OrdenTrabajo> ordenTrabajos = new ArrayList<>();
    private OrdenTrabajoAdapter ordenTrabajoAdapter;
    private RecyclerView recyclerView;
    Button btn_buscar;
    EditText txtBuscarOT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_ot_layout);

        getSupportActionBar().hide();

        btn_buscar = (Button) findViewById(R.id.btn_buscar);
        txtBuscarOT = (EditText) findViewById(R.id.txtBuscatOT);

        recyclerView = (RecyclerView)findViewById(R.id.buscar_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").addConverterFactory(GsonConverterFactory.create()).build();
                APIPerumotor apiPerumotor=retrofit.create(APIPerumotor.class);
                Call<List<OrdenTrabajo>> call=apiPerumotor.getOrdenTrabajo2(txtBuscarOT.getText().toString().trim());

                call.enqueue(new Callback<List<OrdenTrabajo>>() {
                    @Override
                    public void onResponse(Call<List<OrdenTrabajo>> call, Response<List<OrdenTrabajo>> response) {

                        ordenTrabajos = new  ArrayList<>(response.body());
                        ordenTrabajoAdapter= new OrdenTrabajoAdapter(BuscarOTActivity.this,ordenTrabajos);
                        recyclerView.setAdapter(ordenTrabajoAdapter);
                        Toast.makeText(BuscarOTActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<List<OrdenTrabajo>> call, Throwable t) {
                        Toast.makeText(BuscarOTActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
