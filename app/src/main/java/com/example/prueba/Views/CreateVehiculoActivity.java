package com.example.prueba.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.prueba.Interface.APIPerumotor;
import com.example.prueba.Models.PostVehiculo;
import com.example.prueba.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateVehiculoActivity extends AppCompatActivity {

    Button btn_crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vehiculo);

        btn_crear = (Button) findViewById(R.id.btn_crearvehiculo);

        createOrdenTrabajo();
    }

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private void createOrdenTrabajo() {
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateVehiculoActivity.this, "Creando Vehiculo ...", Toast.LENGTH_LONG).show();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
                APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
                Call<PostVehiculo> call = apiPerumotor.createVehiculo(1+"",21+"",3+"",
                        123+"",42+"",4+"",
                        23+"",4+"",3+"",5+"");

                call.enqueue(new Callback<PostVehiculo>() {
                    @Override
                    public void onResponse(Call<PostVehiculo> call, Response<PostVehiculo> response) {

                    }

                    @Override
                    public void onFailure(Call<PostVehiculo> call, Throwable t) {

                    }
                });

            }
        });


    }


}
