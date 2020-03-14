package com.example.prueba.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.prueba.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);


        /*private void getVehiculo() {
            Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").addConverterFactory(GsonConverterFactory.create()).build();
            APIPerumotor apiPerumotor=retrofit.create(APIPerumotor.class);
            Call<List<Vehiculo>> call = apiPerumotor.getVehiculo(placa);




            call.enqueue(new Callback<List<Vehiculo>>() {
                @Override
                public void onResponse(Call<List<Vehiculo>> call, Response<List<Vehiculo>> response) {

                    List<Vehiculo> vehiculos = response.body();

                    for (Vehiculo vehiculo : vehiculos) {

                        String content = vehiculo.getIDVEHICULO() + "\n";

                        txtMarca.append(content);
                    }

                Log.d("idvehiculo", response.body().getIDVEHICULO());
                txtMarca.setText(response.body().getDSMARCA());
                txtModelo.setText(response.body().getDSMODELO());
                txtAño.setText(response.body().getANIO());
                txtColor.setText(response.body().getDSCOLOR());
                txtPlaca.setText(response.body().getPLACA());
                txtModeloTDP.setText(response.body().getCODIGOTDP());
                txtNroMotor.setText(response.body().getNROMOTOR());
                txtNroChasis.setText(response.body().getNROCHASIS());
                }*/
    }
}
