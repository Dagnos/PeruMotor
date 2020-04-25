package com.example.prueba.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prueba.Interface.RetrofitClient;
import com.example.prueba.Models.Data;
import com.example.prueba.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText txt_usuario, txt_contraseña;
    Button btn_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        getSupportActionBar().hide();

        txt_usuario = (EditText) findViewById(R.id.txtUsuario);
        txt_contraseña = (EditText) findViewById(R.id.txtContraseña);

        btn_ingresar = (Button) findViewById(R.id.btnIngresar);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Data> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .login(txt_usuario.getText().toString().trim(),txt_contraseña.getText().toString().trim());

                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {

                        if(response.code() == 200){

                            //Log.d("user",response.body().getData().get(0))
                            Log.d("User",response.body().getData().get(0).getNombre());


                            //intent put extra..
                            //STart activity

                        }
                        else{

                            //error


                        }

                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {

                    }
                });

            }
        });

    }


}
