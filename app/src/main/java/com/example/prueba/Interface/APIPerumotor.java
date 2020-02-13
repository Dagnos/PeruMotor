package com.example.prueba.Interface;

import com.example.prueba.Models.OrdenTrabajo;
import com.example.prueba.Models.Vehiculo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

import retrofit2.http.Path;

public interface APIPerumotor {

   //orden de trabajo consultas
   @GET("ordentrabajo/")
   Call<List<OrdenTrabajo>> getOrdenTrabajo();

   @GET("ordentrabajo/busqueda/{id}")
   Call<List<OrdenTrabajo>> getOrdenTrabajo2(@Path("id") String IDVEHICULO);

   //vehiculos consulta
   @GET("vehiculo/busqueda/{id}")
   Call<Vehiculo> getVehiculo(@Path("id")String PLACA);
}
